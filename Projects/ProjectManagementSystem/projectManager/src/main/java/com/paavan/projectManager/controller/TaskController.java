package com.paavan.projectManager.controller;

import com.paavan.projectManager.entites.Task;
import com.paavan.projectManager.entites.TaskStatus;
import com.paavan.projectManager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PutMapping("/{id}/name")
    public void updateTaskName(@PathVariable Long id,
                               @RequestBody String newName) {

        taskService.updateTaskName(id, newName);
    }

    @PutMapping("/{id}/description")
    public void updateDescription(@PathVariable Long id,
                                  @RequestBody String description) {

        taskService.updateDescription(id, description);
    }

    @PutMapping("/{id}/assignee")
    public void assignUser(@PathVariable Long id,
                           @RequestBody String assignee) {

        taskService.assignUser(id, assignee);
    }

    @PutMapping("/{id}/deadline")
    public void updateDeadline(@PathVariable Long id,
                               @RequestBody LocalDate deadline) {

        taskService.updateDeadline(id, deadline);
    }

    @PutMapping("/{id}/status")
    public void changeStatus(@PathVariable Long id,
                             @RequestBody TaskStatus status) {

        taskService.changeStatus(id, status);
    }

    @GetMapping("/assignee/{assignee}")
    public List<Task> getAssignedTasks(@PathVariable String assignee) {

        return taskService.getAssignedTasks(assignee);
    }
}