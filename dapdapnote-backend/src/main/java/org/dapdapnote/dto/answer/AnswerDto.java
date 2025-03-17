package org.dapdapnote.dto.answer;

import lombok.*;
import org.dapdapnote.entity.AnswerEntity;

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
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public AnswerDto(AnswerEntity entity) {
        seq = entity.getSeq();
        content = entity.getContent();
        writerSeq = entity.getWriter().getSeq();
        writerName = entity.getWriter().getName();
        regDate = entity.getRegDate();
        modDate = entity.getModDate();
    }
}