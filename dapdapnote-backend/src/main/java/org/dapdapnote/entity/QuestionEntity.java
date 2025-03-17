package org.dapdapnote.entity;

import jakarta.persistence.*;
import lombok.*;
import org.dapdapnote.enums.AnswerPermission;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_seq")
    private UserEntity writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_seq")
    private GroupEntity group;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AnswerPermission answerPermission = AnswerPermission.PRIVATE;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<AnswerEntity> answers = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;
}
