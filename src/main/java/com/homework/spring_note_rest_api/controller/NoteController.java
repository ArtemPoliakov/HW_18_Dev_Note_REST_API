package com.homework.spring_note_rest_api.controller;

import com.homework.spring_note_rest_api.controller.dto.NoteDto;
import com.homework.spring_note_rest_api.controller.request.NoteCreateRequest;
import com.homework.spring_note_rest_api.controller.response.NoteCreateResponse;
import com.homework.spring_note_rest_api.controller.response.NoteFindResponse;
import com.homework.spring_note_rest_api.controller.response.NoteUpdateDeleteResponse;
import com.homework.spring_note_rest_api.data.model.Note;
import com.homework.spring_note_rest_api.service.NoteRequestValidator;
import com.homework.spring_note_rest_api.service.NoteService;
import com.homework.spring_note_rest_api.service.NoteStateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {
    private static final String OK_MSG = "ok";
    private static final String INVALID_CONTENT_MSG = "title and content length should be more or equal to 3";
    private static final String INTERNAL_ERROR_MSG = "internal server error";
    private static final String NO_SUCH_NOTE_MSG = "no note by id found";

    private final NoteService noteService;
    private final NoteStateConverter converter;
    private final NoteRequestValidator validator;

    @PostMapping("/create")
    public ResponseEntity<NoteCreateResponse> createNote(@RequestBody NoteCreateRequest request) {
        NoteCreateResponse noteCreateResponse;
        HttpStatus status;
        try {
            if (validator.validateCreateRequest(request)) {
                Note note = converter.dtoToEntity(converter.createRequestToDto(request));
                noteService.createNote(note);
                noteCreateResponse = new NoteCreateResponse(note.getId(), true, OK_MSG);
                status = HttpStatus.OK;
            } else {
                noteCreateResponse = new NoteCreateResponse(null, false, INVALID_CONTENT_MSG);
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            noteCreateResponse = new NoteCreateResponse(null, false, INTERNAL_ERROR_MSG);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(noteCreateResponse, status);
    }

    @PostMapping("/update")
    public ResponseEntity<NoteUpdateDeleteResponse> updateNote(@RequestBody NoteDto noteDto) {
        NoteUpdateDeleteResponse noteUpdateDeleteResponse;
        HttpStatus status;
        try {
            if (validator.validateUpdateRequest(noteDto)) {
                Note note = converter.dtoToEntity(noteDto);
                noteService.updateNote(note);
                noteUpdateDeleteResponse = new NoteUpdateDeleteResponse(true, OK_MSG);
                status = HttpStatus.OK;
            } else {
                noteUpdateDeleteResponse = new NoteUpdateDeleteResponse(false, INVALID_CONTENT_MSG);
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            noteUpdateDeleteResponse = new NoteUpdateDeleteResponse(false, INTERNAL_ERROR_MSG);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(noteUpdateDeleteResponse, status);
    }
    @PostMapping("/delete")
    public ResponseEntity<NoteUpdateDeleteResponse> deleteNote(@RequestParam UUID id){
        NoteUpdateDeleteResponse noteUpdateDeleteResponse;
        HttpStatus status;
        try{
            noteService.deleteById(id);
            noteUpdateDeleteResponse = new NoteUpdateDeleteResponse(true, OK_MSG);
            status = HttpStatus.OK;
        } catch (Exception e){
            noteUpdateDeleteResponse = new NoteUpdateDeleteResponse(false, INTERNAL_ERROR_MSG);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(noteUpdateDeleteResponse, status);
    }
    @GetMapping("/get")
    public ResponseEntity<NoteFindResponse> findNoteById(@RequestParam UUID id){
        NoteFindResponse noteFindResponse;
        HttpStatus status;
        try{
            Note note = noteService.getById(id);
            noteFindResponse = new NoteFindResponse(converter.entityToDto(note), true, OK_MSG);
            status = HttpStatus.OK;
        } catch(NoSuchElementException nse){
            noteFindResponse = new NoteFindResponse(null, false, NO_SUCH_NOTE_MSG);
            status = HttpStatus.NOT_FOUND;
        } catch(Exception e){
            noteFindResponse = new NoteFindResponse(null, false, INTERNAL_ERROR_MSG);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(noteFindResponse, status);
    }
}
