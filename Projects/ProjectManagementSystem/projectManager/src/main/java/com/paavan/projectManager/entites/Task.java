package com.paavan.projectManager.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

    public Task(String taskName,
                String taskDescription,
                LocalDate deadLine,
                ProjectEntity project,
                String assignee,
                TaskStatus taskStatus) {

        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.project = project;
        this.assignee = assignee;
        this.status = taskStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @NotBlank(message = "Task Name cannot be blank")
    @Size(min = 3, max = 30, message = "Task Name must be between 3 and 30 characters")
    private String taskName;

    @Size(min = 3, max = 50, message = "Task Description must be between 3 and 30 characters")
    private String taskDescription;

    private LocalDate deadLine;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private ProjectEntity project;

    @NotNull
    private String assignee;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;

    @Override
    public String toString() {
        return this.taskName + " " + this.taskId;
    }
}