package com.homework.spring_note_rest_api.service;

import com.homework.spring_note_rest_api.controller.dto.NoteDto;
import com.homework.spring_note_rest_api.controller.request.NoteCreateRequest;
import com.homework.spring_note_rest_api.data.model.Note;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NoteStateConverter {
    public NoteDto createRequestToDto(NoteCreateRequest request){
        return new NoteDto(UUID.randomUUID(), request.getTitle(), request.getContent());
    }
    public Note dtoToEntity(NoteDto dto){
        return new Note(dto.getId(), dto.getTitle(), dto.getContent());
    }
    public NoteDto entityToDto(Note entity){
        return new NoteDto(entity.getId(), entity.getTitle(), entity.getContent());
    }
}
