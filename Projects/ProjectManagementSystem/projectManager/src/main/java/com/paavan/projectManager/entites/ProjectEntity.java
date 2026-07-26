package com.paavan.projectManager.entites;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class ProjectEntity {

    public ProjectEntity(String projectName, String projectDescription) {

        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long projectId;

    @Getter
    @Setter
    @NotBlank(message = "Project Name cannot be blank")
    @Size(min = 3, max = 30, message = "Project Name must be between 3 and 30 characters")
    private String projectName;

    @Getter
    @Setter
    @NotBlank(message = "Project Description cannot be blank")
    @Size(min = 3, max = 100, message = "Project Description must be between 3 and 100 characters")
    private String projectDescription;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter
    private List<Task> allTasks = new ArrayList<>();

    public void addTask(Task task) {
        allTasks.add(task);
        task.setProject(this);
    }

    public void removeTask(Task task) {
        allTasks.remove(task);
        task.setProject(null);
    }

    @Override
    public String toString() {
        return this.projectName + " " + this.projectId;
    }
}