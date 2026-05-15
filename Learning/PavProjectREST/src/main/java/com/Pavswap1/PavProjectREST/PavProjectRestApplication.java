package com.Pavswap1.PavProjectREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PavProjectRestApplication {

	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(PavProjectRestApplication.class, args);
	}

	PavProjectRestApplication(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
}
