package com.paavan.notesAPI.service;

import com.paavan.notesAPI.entities.NoteEntity;
import com.paavan.notesAPI.repository.NotesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {

    private NotesRepository notesRepository;

    public void addNote(NoteEntity note) {
        notesRepository.save(note);
    }

    public void removeNote(Long noteId) {
        notesRepository.deleteById(noteId);
    }

    public List<NoteEntity> getAllNotes() {
        return notesRepository.findAll();
    }
}