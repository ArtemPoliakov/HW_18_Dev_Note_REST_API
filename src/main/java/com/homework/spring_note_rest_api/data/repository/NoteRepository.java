package com.homework.spring_note_rest_api.data.repository;

import com.homework.spring_note_rest_api.data.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {
}
