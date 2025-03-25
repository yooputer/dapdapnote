package org.dapdapnote.dto.expression;

import lombok.Getter;
import lombok.Setter;
import org.dapdapnote.entity.Expression;
import org.dapdapnote.entity.Note;
import org.dapdapnote.entity.User;
import org.dapdapnote.utils.ListUtil;

import java.util.List;

@Getter
@Setter
public class SaveExpressionRequest {
    private Long seq;
    private String korean;
    private List<String> englishList;

    public Expression toEntity(Note note) {
        return Expression.builder()
                .seq(seq)
                .korean(korean)
                .englishList(ListUtil.listToJson(englishList))
                .writer(note.getWriter())
                .build();
    }
}