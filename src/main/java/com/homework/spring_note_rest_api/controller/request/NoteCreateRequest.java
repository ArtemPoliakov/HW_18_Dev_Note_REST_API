package com.homework.spring_note_rest_api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteCreateRequest {
    private String title;
    private String content;
}
