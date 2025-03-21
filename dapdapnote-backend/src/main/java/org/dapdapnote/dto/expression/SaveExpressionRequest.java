package org.dapdapnote.dto.expression;

import lombok.Getter;
import lombok.Setter;
import org.dapdapnote.entity.Note;
import org.dapdapnote.entity.User;

import java.util.List;

@Getter
@Setter
public class SaveExpressionRequest {
    private Long seq;
    private String korean;
    private List<String> englishList;

    // Back에서 세팅
    private User writer;
    private Note note;
}