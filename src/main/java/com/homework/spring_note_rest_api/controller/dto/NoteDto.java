package com.homework.spring_note_rest_api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class NoteDto {
    private UUID id;
    private String title;
    private String content;
}
