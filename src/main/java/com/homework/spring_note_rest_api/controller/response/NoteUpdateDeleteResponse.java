package com.homework.spring_note_rest_api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteUpdateDeleteResponse {
    private boolean success;
    private String error;
}
