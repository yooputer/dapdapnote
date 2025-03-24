package org.dapdapnote.controller;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.note.NoteSearchOptions;
import org.dapdapnote.dto.note.NoteListDto;
import org.dapdapnote.dto.note.SaveNoteRequest;
import org.dapdapnote.dto.note.NoteDto;
import org.dapdapnote.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/note")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ResponseEntity<List<NoteListDto>> getList(
            @RequestParam(required = false) boolean onlyUnsolved,
            @RequestParam(required = false) boolean onlyToday,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword
    ) {
        NoteSearchOptions request = new NoteSearchOptions();
        request.setOnlyUnsolved(onlyUnsolved);
        request.setOnlyToday(onlyToday);
        request.setSearchType(searchType);
        request.setSearchKeyword(searchKeyword);

        List<NoteListDto> noteList = noteService.getList(request);

        return ResponseEntity.ok(noteList);
    }

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