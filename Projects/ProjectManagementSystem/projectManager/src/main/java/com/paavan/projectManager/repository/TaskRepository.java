package com.paavan.projectManager.repository;

import com.paavan.projectManager.entites.Task;
import com.paavan.projectManager.entites.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject_ProjectId(Long projectId);

    List<Task> findByProject_ProjectIdAndStatus(
            Long projectId,
            TaskStatus status
    );

    List<Task> findByAssignee(String assignee);

}