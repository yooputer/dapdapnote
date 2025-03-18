package org.dapdapnote.dto.answer;

import lombok.*;
import org.dapdapnote.entity.AnswerEntity;
import org.dapdapnote.utils.DateUtil;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnswerDto {
    private Long seq;
    private String content;
    private Long writerSeq;
    private String writerName;
    private String regDateStr;
    private String regTimeStr;

    public AnswerDto(AnswerEntity entity) {
        seq = entity.getSeq();
        content = entity.getContent();
        writerSeq = entity.getWriter().getSeq();
        writerName = entity.getWriter().getName();
        regDateStr = DateUtil.toDateStr(entity.getRegDate());
        regTimeStr = DateUtil.toTimeStr(entity.getRegDate());
    }
}