package com.paavan.notesAPI.controller;

import com.paavan.notesAPI.entities.NoteEntity;
import com.paavan.notesAPI.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notesApi")
@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://127.0.0.1:5500"
})
@AllArgsConstructor
public class notesController {

    private NoteService noteService;

    @GetMapping("/hello")
    public String helloTxt() {
        return "hello";
    }

    @PostMapping
    public void addNote(@RequestBody NoteEntity note) {
        noteService.addNote(note);
    }

    @DeleteMapping("/delete/{noteId}")
    public void removeNote(@PathVariable Long noteId) {
        noteService.removeNote(noteId);
    }

    @GetMapping("/notes")
    public List<NoteEntity> getAllNotes() {
        return noteService.getAllNotes();
    }
}
