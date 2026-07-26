package com.paavan.projectManager.service;

import com.paavan.projectManager.entites.ProjectEntity;
import com.paavan.projectManager.entites.Task;
import com.paavan.projectManager.entites.TaskStatus;
import com.paavan.projectManager.exception.ProjectNotFoundException;
import com.paavan.projectManager.exception.TaskNotFoundException;
import com.paavan.projectManager.repository.ProjectRepository;
import com.paavan.projectManager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Task createTask(Long projectId, Task task) {

        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        project.addTask(task);

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        ProjectEntity project = task.getProject();

        if(project != null)
            project.removeTask(task);

        taskRepository.delete(task);
    }

    @Transactional
    public void updateTaskName(Long id, String newName){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTaskName(newName);
    }

    @Transactional
    public void updateDescription(Long id, String description){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTaskDescription(description);
    }

    @Transactional
    public void assignUser(Long id, String assignee){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setAssignee(assignee);
    }

    @Transactional
    public void updateDeadline(Long id, LocalDate deadline){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setDeadLine(deadline);
    }

    @Transactional
    public void changeStatus(Long id, TaskStatus status){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setStatus(status);
    }

    public List<Task> getProjectTasks(Long projectId){

        if(projectId == null) {
            throw new IllegalArgumentException("Project id cannot be null");
        }

        return taskRepository.findByProject_ProjectId(projectId);
    }

    public List<Task> getTaskByStatus(Long projectId, TaskStatus status){

        if(projectId == null) {
            throw new IllegalArgumentException("Project id cannot be null");
        }
        else if(status == null) {
            throw new IllegalArgumentException("TaskStatus cannot be null");
        }

        return taskRepository.findByProject_ProjectIdAndStatus(projectId, status);
    }

    public List<Task> getAssignedTasks(String assignee){

        if(assignee == null) {
            throw new IllegalArgumentException("Assignee cannot be null");
        }

        return taskRepository.findByAssignee(assignee);
    }
}
