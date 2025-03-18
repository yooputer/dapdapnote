package org.dapdapnote.dto.question;

import lombok.*;
import org.dapdapnote.dto.answer.AnswerDto;
import org.dapdapnote.entity.QuestionEntity;
import org.dapdapnote.enums.AnswerPermission;
import org.dapdapnote.utils.DateUtil;

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
    private String regDateStr;
    private String regTimeStr;

    public QuestionDto(QuestionEntity entity) {
        seq = entity.getSeq();
        content = entity.getContent();
        writerSeq = entity.getWriter().getSeq();
        writerName = entity.getWriter().getName();
        answerPermission = entity.getAnswerPermission();
        regDateStr = DateUtil.toDateStr(entity.getRegDate());
        regTimeStr = DateUtil.toTimeStr(entity.getRegDate());

        if (entity.getGroup() != null) {
            groupSeq = entity.getGroup().getSeq();
            groupName = entity.getGroup().getName();
        }
    }
}
