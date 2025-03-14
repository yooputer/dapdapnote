package org.dapdapnote.entity;


import jakarta.persistence.*;
import lombok.*;
import org.dapdapnote.dto.DdNoteDto;
import org.dapdapnote.enums.DdNoteType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "dd_note")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class DdNoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Enumerated(EnumType.STRING)
    private DdNoteType type;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_seq")
    private UserEntity writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_seq")
    private GroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_seq")
    private DdNoteEntity parentDdNote;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;

    public static DdNoteDto toDto(DdNoteEntity note) {
        return DdNoteDto.builder()
                .seq(note.seq)
                .type(note.type)
                .content(note.content)
                .writerSeq(note.writer.getSeq())
                .writerName(note.writer.getName())
                .groupSeq(note.group != null ? note.group.getSeq() : null)
                .groupName(note.group != null ? note.group.getName() : null)
                .parentSeq(note.parentDdNote != null ? note.parentDdNote.getSeq() : null)
                .build();
    }
}
