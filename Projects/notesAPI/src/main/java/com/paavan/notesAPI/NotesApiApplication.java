package com.paavan.notesAPI;

import com.paavan.notesAPI.entities.NoteEntity;
import com.paavan.notesAPI.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class NotesApiApplication implements CommandLineRunner {

	private NoteService noteService;

	public static void main(String[] args) {
		SpringApplication.run(NotesApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
