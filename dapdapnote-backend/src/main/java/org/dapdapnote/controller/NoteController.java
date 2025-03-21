package org.dapdapnote.controller;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.note.SaveNoteRequest;
import org.dapdapnote.dto.note.NoteDto;
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
    public ResponseEntity<NoteDto> getNoteForEdit(@PathVariable Long noteSeq) {
        NoteDto response = noteService.getNoteForEdit(noteSeq);

        return ResponseEntity.ok(response);
    }

}