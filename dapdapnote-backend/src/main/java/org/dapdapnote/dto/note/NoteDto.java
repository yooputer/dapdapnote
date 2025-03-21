package org.dapdapnote.dto.note;

import lombok.*;
import org.dapdapnote.dto.expression.ExpressionDto;
import org.dapdapnote.entity.Note;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NoteDto {
    private Long seq;
    private String korean;
    private String english;
    private List<ExpressionDto> expressionList;
    private String status;

    public NoteDto(Note note) {
        seq = note.getSeq();
        korean = note.getKorean();
        english = note.getEnglish();
        expressionList = note.getExpressions().stream().map(ExpressionDto::new).toList();
        status = note.getStatus();
    }
}