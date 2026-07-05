const noteList = document.querySelector("#note-list");
const addNoteButton = document.querySelector("#add-note");
const removeNoteButton = document.querySelector("#remove-note");


async function getNoteList() {

    const response = await fetch("http://localhost:8080/notesApi/notes");

    const notes = await response.json();

    noteList.innerHTML = "";

    notes.forEach(note => {

        const li = document.createElement("li");

        li.innerHTML = `
            <h3>${note.noteTitle}</h3>
            <p>${note.note}</p>
            <small>ID : ${note.noteId}</small>
        `;

        noteList.appendChild(li);

    });

}

getNoteList();

async function addNote() {

    const title = prompt("Enter title");

    if(title == null) return;

    const note = prompt("Enter note");

    if(note == null) return;

    const response = await fetch("http://localhost:8080/notesApi", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            noteTitle: title,

            note: note

        })

    });

    if(response.ok){

        getNoteList();

    }

}

async function removeNote(){

    const id = prompt("Enter Note ID");

    if(id == null) return;

    const response = await fetch(

        `http://localhost:8080/notesApi/delete/${id}`,

        {

            method: "DELETE"

        }

    );

    if(response.ok){

        getNoteList();

    }

}

addNoteButton.addEventListener("click", addNote);

removeNoteButton.addEventListener("click", removeNote);