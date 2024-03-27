package com.homework.spring_note_rest_api.controller.response;

import com.homework.spring_note_rest_api.controller.dto.NoteDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteFindResponse {
    private NoteDto note;
    private boolean success;
    private String error;
}
