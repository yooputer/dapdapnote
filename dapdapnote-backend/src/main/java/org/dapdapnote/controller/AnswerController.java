package org.dapdapnote.controller;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.answer.CreateAnswerRequest;
import org.dapdapnote.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<Long> addQuestion(@RequestBody CreateAnswerRequest request){
        Long answerSeq = answerService.createAnswer(request);

        return ResponseEntity.ok(answerSeq);
    }
}
