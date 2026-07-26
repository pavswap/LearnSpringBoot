package com.paavan.projectManager.controller;

import com.paavan.projectManager.entites.ProjectEntity;
import com.paavan.projectManager.entites.Task;
import com.paavan.projectManager.entites.TaskStatus;
import com.paavan.projectManager.service.ProjectService;
import com.paavan.projectManager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    private TaskService taskService;

    @PostMapping
    public void newProject(@RequestBody ProjectEntity project) {
        projectService.newProject(project);
    }

    @PostMapping("/{projectId}/createTask")
    public Task createTask(@PathVariable Long projectId,
                           @RequestBody Task task) {

        return taskService.createTask(projectId, task);
    }

    @DeleteMapping("delete/{id}")
    public void removeById(@PathVariable Long id) {
        projectService.removeProjectById(id);
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    public void deleteTaskFromProject(
            @PathVariable Long projectId,
            @PathVariable Long taskId
    ) {

        taskService.deleteTask(taskId);
    }

    @GetMapping("/allProjects")
    public List<ProjectEntity> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{projectId}/tasks")
    public List<Task> getTasks(@PathVariable Long projectId) {
        return taskService.getProjectTasks(projectId);
    }

    @GetMapping("/{projectId}/todoTasks")
    public  List<Task> getTodoTasks(@PathVariable Long projectId) {
        return taskService.getTaskByStatus(projectId, TaskStatus.TODO);
    }

    @GetMapping("/{projectId}/inProgressTasks")
    public  List<Task> getInProgressTasks(@PathVariable Long projectId) {
        return taskService.getTaskByStatus(projectId, TaskStatus.IN_PROGRESS);
    }

    @GetMapping("/{projectId}/completedTasks")
    public  List<Task> getCompletedTasks(@PathVariable Long projectId) {
        return taskService.getTaskByStatus(projectId, TaskStatus.COMPLETED);
    }

    @PutMapping("update/name/{id}")
    public void updateProjectName(@PathVariable Long id,@RequestBody String name) {
        projectService.updateProjectName(id, name);
    }

    @PutMapping("update/description/{id}")
    public void updateProjectDescription(@PathVariable Long id, @RequestBody String description) {
        projectService.updateProjectDescription(id, description);
    }
}
