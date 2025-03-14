package org.dapdapnote.dto;

import lombok.*;
import org.dapdapnote.enums.DdNoteType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DdNoteDto {
    private Long seq;
    private DdNoteType type;
    private String content;
    private Long writerSeq;
    private String writerName;
    private Long groupSeq;
    private String groupName;
    private Long parentSeq;
}