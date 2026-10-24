const $=s=>document.querySelector(s);
const STATUS=[['TODO','To do'],['IN_PROGRESS','In progress'],['COMPLETED','Done']];
let API=localStorage.getItem('pm_api')??(location.protocol.startsWith('http')?'':'http://localhost:8080');
let projects=[],pid=null,tasks=[],who=null,drag=null;

const esc=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const hue=s=>{let h=0;for(const c of String(s))h=(h*31+c.charCodeAt(0))%360;return `hsl(${h} 55% 45%)`};
const ini=s=>String(s).trim().split(/\s+/).map(w=>w[0]).join('').slice(0,2).toUpperCase();
function toast(m,err){const t=$('#toast');t.textContent=m;t.className='show'+(err?' err':'');clearTimeout(t.h);t.h=setTimeout(()=>t.className='',2600)}

async function req(p,m='GET',b,type='json'){
  const o={method:m,headers:{}};
  if(b!==undefined){o.headers['Content-Type']=type==='text'?'text/plain':'application/json';o.body=type==='text'?b:JSON.stringify(b)}
  const r=await fetch(API+p,o);
  if(!r.ok)throw new Error(`${m} ${p} failed (${r.status})`);
  const x=await r.text();return x?JSON.parse(x):null;
}
const run=async(f,ok)=>{try{await f();if(ok)toast(ok)}catch(e){toast(e.message.includes('fetch')?'Cannot reach the server. Check the URL and CORS settings.':e.message,1)}};

const getProjects=async()=>(await req('/project/allProjects')||[]).map(p=>({id:p.projectId,name:p.projectName,description:p.projectDescription}));
async function loadProjects(select){
  projects=await getProjects();
  if(select)pid=select; else if(!projects.some(p=>p.id===pid))pid=projects[0]?.id??null;
  renderSide();await loadTasks();
}
async function loadTasks(){
  tasks=pid?(await req(`/project/${pid}/tasks`)||[]).map(t=>({id:t.taskId,name:t.taskName,description:t.taskDescription,deadline:t.deadLine,assignee:t.assignee,status:t.status})):[];
  renderTop();renderBoard();
}

function renderSide(){
  $('#apiLbl').textContent=API||location.origin;
  $('#plist').innerHTML=projects.map(p=>`<button class="pitem ${p.id===pid?'on':''}" data-id="${p.id}"><span class="pic" style="background:${hue(p.name)}">${esc(ini(p.name||'?'))}</span><span class="n">${esc(p.name)}</span></button>`).join('')||'<div style="padding:10px;font-size:13px">No projects yet</div>';
}
function renderTop(){
  const p=projects.find(x=>x.id===pid);
  $('#top').hidden=!p;if(!p)return;
  $('#crumb').textContent=p.name;
  if(document.activeElement!==$('#pname'))$('#pname').value=p.name||'';
  if(document.activeElement!==$('#pdesc'))$('#pdesc').value=p.description||'';
  const people=[...new Set(tasks.map(t=>t.assignee).filter(Boolean))];
  $('#chips').innerHTML=people.map(a=>`<button class="chip ${who===a?'on':''}" data-a="${esc(a)}" title="${esc(a)}" style="background:${hue(a)}">${esc(ini(a))}</button>`).join('');
}
function renderBoard(){
  const b=$('#board');
  if(!pid){b.innerHTML='<div class="empty"><h2>Create your first project</h2>Projects hold the tasks your team tracks on a board.<br><br><button class="btn" onclick="newProject()">Create project</button></div>';return}
  const q=$('#q').value.toLowerCase();
  const vis=tasks.filter(t=>(!who||t.assignee===who)&&(!q||`${t.name} ${t.description} ${t.assignee}`.toLowerCase().includes(q)));
  const today=new Date().toISOString().slice(0,10);
  b.innerHTML=STATUS.map(([k,l])=>{
    const list=vis.filter(t=>(t.status||'TODO')===k);
    return `<section class="col" data-s="${k}"><h3>${l} <b>${list.length}</b></h3>${list.map(t=>`
      <button class="card" draggable="true" data-id="${t.id}">
        <div class="t">${esc(t.name)}</div>
        <div class="meta"><span class="key">T-${t.id}</span>${t.deadline?`<span class="due ${t.deadline<today&&k!=='COMPLETED'?'late':''}">${esc(t.deadline)}</span>`:''}
        ${t.assignee?`<span class="chip" style="background:${hue(t.assignee)}" title="${esc(t.assignee)}">${esc(ini(t.assignee))}</span>`:''}</div>
      </button>`).join('')}
      <button class="add" data-add="${k}">+ Create task</button></section>`}).join('');
}

/* events */
$('#plist').onclick=e=>{const b=e.target.closest('.pitem');if(b){pid=+b.dataset.id;who=null;renderSide();run(loadTasks)}};
$('#chips').onclick=e=>{const c=e.target.closest('.chip');if(c){who=who===c.dataset.a?null:c.dataset.a;renderTop();renderBoard()}};
$('#q').oninput=renderBoard;
$('#board').onclick=e=>{
  const a=e.target.closest('[data-add]');if(a)return taskDialog(null,a.dataset.add);
  const c=e.target.closest('.card');if(c)taskDialog(tasks.find(t=>t.id==c.dataset.id));
};
$('#board').ondragstart=e=>{const c=e.target.closest('.card');if(c){drag=+c.dataset.id;c.classList.add('drag');e.dataTransfer.effectAllowed='move'}};
$('#board').ondragend=e=>{e.target.classList?.remove('drag');document.querySelectorAll('.col.over').forEach(c=>c.classList.remove('over'))};
$('#board').ondragover=e=>{const c=e.target.closest('.col');if(c){e.preventDefault();document.querySelectorAll('.col.over').forEach(x=>x!==c&&x.classList.remove('over'));c.classList.add('over')}};
$('#board').ondrop=e=>{
  const c=e.target.closest('.col');if(!c||drag==null)return;e.preventDefault();
  const t=tasks.find(x=>x.id===drag);drag=null;
  if(t&&t.status!==c.dataset.s)setStatus(t,c.dataset.s);else renderBoard();
};
async function setStatus(t,s){
  const old=t.status;t.status=s;renderBoard();
  try{await req(`/task/${t.id}/status`,'PUT',s)}catch(e){t.status=old;renderBoard();toast(e.message,1)}
}

const cur=()=>projects.find(x=>x.id===pid);
$('#pname').onchange=()=>{const v=$('#pname').value.trim();if(v.length<3||v.length>30){$('#pname').value=cur().name;return toast('Project name must be 3 to 30 characters',1)}run(async()=>{await req(`/project/update/name/${pid}`,'PUT',$('#pname').value,'text');await loadProjects(pid)},'Project renamed')};
$('#pdesc').onchange=()=>{const v=$('#pdesc').value.trim();if(v.length<3||v.length>100){$('#pdesc').value=cur().description||'';return toast('Project description must be 3 to 100 characters',1)}run(async()=>{await req(`/project/update/description/${pid}`,'PUT',$('#pdesc').value,'text');await loadProjects(pid)},'Description saved')};
$('#delProj').onclick=()=>{const p=projects.find(x=>x.id===pid);if(confirm(`Delete "${p.name}" and its tasks?`))run(async()=>{await req(`/project/delete/${pid}`,'DELETE');pid=null;await loadProjects()},'Project deleted')};
$('#newTask').onclick=()=>taskDialog(null,'TODO');
$('#newProj').onclick=newProject;
$('#apiBtn').onclick=()=>{const v=prompt('Backend base URL (leave empty for same origin):',API);if(v!==null){API=v.trim().replace(/\/$/,'');localStorage.setItem('pm_api',API);boot()}};

/* dialogs */
const dlg=$('#dlg');
const close=()=>dlg.close();
dlg.onclick=e=>{if(e.target===dlg||e.target.closest('.x'))close()};

function newProject(){
  dlg.innerHTML=`<div class="mh"><b>Create project</b><button class="x" aria-label="Close">×</button></div>
  <div class="mb single"><div><div class="f"><label for="n">Name</label><input id="n" autofocus></div>
  <div class="f"><label for="d">Description</label><textarea id="d" style="min-height:80px"></textarea></div></div></div>
  <div class="mf"><span></span><button class="btn" id="ok">Create project</button></div>`;
  dlg.showModal();
  $('#ok').onclick=()=>{const name=$('#n').value.trim(),desc=$('#d').value.trim();
    if(name.length<3||name.length>30)return toast('Project name must be 3 to 30 characters',1);
    if(desc.length<3||desc.length>100)return toast('Project description must be 3 to 100 characters',1);
    run(async()=>{const before=new Set(projects.map(p=>p.id));
      await req('/project','POST',{projectName:name,projectDescription:desc});close();
      projects=await getProjects();
      const nu=projects.find(p=>!before.has(p.id));await loadProjects(nu?.id)},'Project created')};
}

function taskDialog(t,status='TODO'){
  const isNew=!t;
  dlg.innerHTML=`<div class="mh"><b>${isNew?'Create task':'T-'+t.id}</b><button class="x" aria-label="Close">×</button></div>
  <div class="mb"><div>
    <div class="f"><label for="tn">Summary</label><input id="tn" class="big" value="${esc(t?.name)}"></div>
    <div class="f"><label for="td">Description</label><textarea id="td" placeholder="Add a description">${esc(t?.description)}</textarea></div>
  </div><div>
    <div class="f"><label for="ts">Status</label><select id="ts">${STATUS.map(([k,l])=>`<option value="${k}" ${(t?.status||status)===k?'selected':''}>${l}</option>`).join('')}</select></div>
    <div class="f"><label for="ta">Assignee</label><input id="ta" value="${esc(t?.assignee)}" placeholder="Unassigned"></div>
    <div class="f"><label for="tdl">Due date</label><input id="tdl" type="date" value="${esc(t?.deadline)}"></div>
  </div></div>
  <div class="mf">${isNew?'<span></span><button class="btn" id="ok">Create task</button>':'<button class="btn danger" id="del">Delete task</button><span style="color:var(--mute);font-size:12px;align-self:center">Changes save automatically</span>'}</div>`;
  dlg.showModal();$('#tn').blur();
  if(isNew){
    $('#ok').onclick=()=>{const name=$('#tn').value.trim(),desc=$('#td').value.trim();
      if(name.length<3||name.length>30)return toast('Summary must be 3 to 30 characters',1);
      if(desc&&(desc.length<3||desc.length>50))return toast('Description must be 3 to 50 characters',1);
      run(async()=>{await req(`/project/${pid}/createTask`,'POST',{taskName:name,taskDescription:desc||null,assignee:$('#ta').value.trim(),deadLine:$('#tdl').value||null,status:$('#ts').value});close();await loadTasks()},'Task created')};
    return;
  }
  const bad=(id,key,m)=>{const c=tasks.find(x=>x.id===t.id)||t;$(id).value=c[key]??'';throw new Error(m)};
  const save=(id,key,fn,msg)=>$(id).onchange=()=>run(async()=>{await fn($(id).value.trim(),(m)=>bad(id,key,m));await loadTasks()},msg);
  save('#tn','name',(v,no)=>{if(v.length<3||v.length>30)no('Summary must be 3 to 30 characters');return req(`/task/${t.id}/name`,'PUT',v,'text')},'Summary saved');
  save('#td','description',(v,no)=>{if(v.length<3||v.length>50)no('Description must be 3 to 50 characters');return req(`/task/${t.id}/description`,'PUT',v,'text')},'Description saved');
  save('#ta','assignee',v=>req(`/task/${t.id}/assignee`,'PUT',v,'text'),'Assignee saved');
  save('#tdl','deadline',v=>v&&req(`/task/${t.id}/deadline`,'PUT',v),'Due date saved');
  save('#ts','status',v=>req(`/task/${t.id}/status`,'PUT',v),'Status updated');
  $('#del').onclick=()=>{if(confirm('Delete this task?'))run(async()=>{await req(`/project/${pid}/tasks/${t.id}`,'DELETE');close();await loadTasks()},'Task deleted')};
}

async function boot(){try{await loadProjects()}catch(e){renderSide();$('#board').innerHTML=`<div class="empty"><h2>Can't reach the server</h2>Tried <code>${esc(API||location.origin)}</code>. Start the Spring Boot app, enable CORS, or change the server URL in the sidebar.</div>`}}
boot();
