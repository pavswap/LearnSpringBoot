package com.Paavan.SpringDataJPA;

import com.Paavan.SpringDataJPA.Entity.Students;
import com.Paavan.SpringDataJPA.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class SpringDataJpaApplication implements CommandLineRunner {

	StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Students students = new Students(3, "koop", false);

		studentService.addStudent(students);

		System.out.println(studentService.findAllStudents());
	}
}
