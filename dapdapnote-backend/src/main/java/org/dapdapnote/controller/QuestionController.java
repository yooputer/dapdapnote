package org.dapdapnote.controller;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.QuestionService;
import org.dapdapnote.dto.question.CreateQuestionRequest;
import org.dapdapnote.dto.question.QuestionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<Long> addQuestion(@RequestBody CreateQuestionRequest request){
        Long questionSeq = questionService.createQuestion(request);

        return ResponseEntity.ok(questionSeq);
    }

    @GetMapping("/my")
    public List<QuestionDto> myQuestion() {
        return questionService.getMyQuestions();
    }
}
