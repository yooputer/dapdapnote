package org.dapdapnote.dto.question;

import lombok.*;
import org.dapdapnote.dto.answer.AnswerDto;
import org.dapdapnote.entity.QuestionEntity;
import org.dapdapnote.enums.AnswerPermission;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuestionDto {
    private Long seq;
    private String content;
    private Long writerSeq;
    private String writerName;
    private Long groupSeq;
    private String groupName;
    private AnswerPermission answerPermission;
    private List<AnswerDto> answerList;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public QuestionDto(QuestionEntity entity) {
        seq = entity.getSeq();
        content = entity.getContent();
        writerSeq = entity.getWriter().getSeq();
        writerName = entity.getWriter().getName();
        answerPermission = entity.getAnswerPermission();
        regDate = entity.getRegDate();
        modDate = entity.getModDate();

        if (entity.getGroup() != null) {
            groupSeq = entity.getGroup().getSeq();
            groupName = entity.getGroup().getName();
        }

        if (entity.getAnswers() != null) {
            answerList = entity.getAnswers().stream()
                    .map(AnswerDto::new)
                    .toList();
        }
    }
}
