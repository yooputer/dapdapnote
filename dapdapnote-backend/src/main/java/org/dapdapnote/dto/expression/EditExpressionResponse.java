package org.dapdapnote.dto.expression;

import lombok.*;
import org.dapdapnote.entity.Expression;
import org.dapdapnote.utils.ListUtil;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EditExpressionResponse {
    private Long seq;
    private String korean;
    private List<String> englishList;

    public EditExpressionResponse(Expression expression) {
        this.seq = expression.getSeq();
        this.korean = expression.getKorean();
        this.englishList = ListUtil.jsonToStringList(expression.getEnglishList());
    }
}