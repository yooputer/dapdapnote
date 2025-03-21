package org.dapdapnote.dto.note;

import lombok.*;
import org.dapdapnote.dto.expression.EditExpressionResponse;
import org.dapdapnote.entity.Note;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EditNoteResponse {
    private Long seq;
    private String korean;
    private String english;
    private List<EditExpressionResponse> expressionList;
    private String status;

    public EditNoteResponse(Note note) {
        seq = note.getSeq();
        korean = note.getKorean();
        english = note.getEnglish();
        expressionList = note.getExpressions().stream().map(EditExpressionResponse::new).toList();
    }
}