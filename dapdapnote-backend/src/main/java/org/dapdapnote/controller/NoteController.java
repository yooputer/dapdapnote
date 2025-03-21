package org.dapdapnote.controller;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.note.SaveNoteRequest;
import org.dapdapnote.dto.note.EditNoteResponse;
import org.dapdapnote.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/note")
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/save")
    public ResponseEntity<Long> editNote(@RequestBody SaveNoteRequest request) {
        Long noteSeq = noteService.saveNote(request);

        return ResponseEntity.ok(noteSeq);
    }

    @GetMapping("/edit/{noteSeq}")
    public ResponseEntity<EditNoteResponse> getNoteForEdit(@PathVariable Long noteSeq) {
        EditNoteResponse response = noteService.getNoteForEdit(noteSeq);

        return ResponseEntity.ok(response);
    }

}