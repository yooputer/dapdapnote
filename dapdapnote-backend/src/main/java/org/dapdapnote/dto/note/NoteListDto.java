package org.dapdapnote.dto.note;

import lombok.*;
import org.dapdapnote.entity.Note;
import org.dapdapnote.utils.DateUtil;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NoteListDto {
    private Long seq;
    private String korean;
    private String status;
    private String regDateTimeStr;
    private Integer expressionCnt;

    public NoteListDto(Note note) {
        seq = note.getSeq();
        korean = note.getKorean();
        status = note.getStatus();
        regDateTimeStr = DateUtil.toDateTimeStr(note.getRegDate());
        expressionCnt = note.getExpressionCnt();
    }
}