package org.dapdapnote.dto.question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionRequest {
    private String content;
    private Long groupSeq;
    private String answerPermission;
}
