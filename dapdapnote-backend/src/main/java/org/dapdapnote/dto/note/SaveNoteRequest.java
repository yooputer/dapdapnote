package org.dapdapnote.dto.note;

import lombok.Getter;
import lombok.Setter;
import org.dapdapnote.dto.expression.SaveExpressionRequest;
import org.dapdapnote.entity.User;

import java.util.List;

@Getter
@Setter
public class SaveNoteRequest {
    private Long seq;
    private String korean;
    private String english;
    private List<SaveExpressionRequest> expressionList;

    // Back에서 세팅
    private User writer;
}