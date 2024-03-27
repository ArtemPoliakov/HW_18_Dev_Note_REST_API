package com.homework.spring_note_rest_api.service;

import com.homework.spring_note_rest_api.controller.dto.NoteDto;
import com.homework.spring_note_rest_api.controller.request.NoteCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class NoteRequestValidator {
    public boolean validateCreateRequest(NoteCreateRequest request){
        return request.getTitle().length()>=3
                && request.getContent().length()>=3;
    }
    public boolean validateUpdateRequest(NoteDto dto){
        return validateCreateRequest(new NoteCreateRequest(dto.getTitle(), dto.getContent()));
    }
}
