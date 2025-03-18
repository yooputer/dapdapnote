package org.dapdapnote.dto.answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnswerRequest {
    private String content;
    private Long questionSeq;
}