package org.dapdapnote.dto.note;

import lombok.*;
import org.dapdapnote.dto.expression.ExpressionDto;
import org.dapdapnote.entity.Note;
import org.dapdapnote.entity.NoteExpression;

import java.util.Comparator;
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
        status = note.getStatus();

        expressionList = note.getNoteExpressions().stream()
                .sorted(Comparator.comparingInt(NoteExpression::getExpressionOrder))
                .map(NoteExpression::getExpression)
                .map(ExpressionDto::new)
                .toList();
    }
}