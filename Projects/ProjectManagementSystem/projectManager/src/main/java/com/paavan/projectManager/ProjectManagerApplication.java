package com.paavan.projectManager;

import com.paavan.projectManager.service.ProjectService;
import com.paavan.projectManager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class ProjectManagerApplication implements CommandLineRunner {

	private ProjectService projectService;
	private TaskService taskService;

	public static void main(String[] args) {

		SpringApplication.run(ProjectManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
