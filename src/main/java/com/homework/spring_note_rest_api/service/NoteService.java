package com.homework.spring_note_rest_api.service;

import com.homework.spring_note_rest_api.data.repository.NoteRepository;
import com.homework.spring_note_rest_api.data.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    public void createNote(Note note){
        noteRepository.save(note);
    }
    public void deleteById(UUID id){
        noteRepository.deleteById(id);
    }
    public void updateNote(Note note){
        noteRepository.save(note);
    }
    public Note getById(UUID id) throws NoSuchElementException{
        return noteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
