package com.paavan.notesAPI.repository;

import com.paavan.notesAPI.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findAll();

    void deleteById(Long id);
}