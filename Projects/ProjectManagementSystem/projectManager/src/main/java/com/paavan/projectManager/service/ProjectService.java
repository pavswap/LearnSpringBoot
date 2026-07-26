package com.paavan.projectManager.service;

import com.paavan.projectManager.entites.ProjectEntity;
import com.paavan.projectManager.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;

    public void newProject(ProjectEntity project) {

        if(project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }

        projectRepository.save(project);
    }

    public void removeProjectById(Long id) {

        if(id == null) {
            throw new IllegalArgumentException("Project id cannot be null");
        }

        projectRepository.deleteById(id);
    }

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    @Transactional
    public void updateProjectName(Long id, String name) {

        if(id == null) {
            throw new IllegalArgumentException("Project id cannot be null");
        }
        else if(name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setProjectName(name);
    }

    @Transactional
    public void updateProjectDescription(Long id, String description) {

        if(id == null) {
            throw new IllegalArgumentException("Project id cannot be null");
        }
        else if(description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }

        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setProjectDescription(description);
    }
}
