package org.dapdapnote.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Expression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private String korean;

    @Column(nullable = false)
    private String englishList;

    @ManyToOne
    @JoinColumn(name = "writer_seq", nullable = false)
    private User writer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "note_expression",
            joinColumns = @JoinColumn(name = "note_seq"),
            inverseJoinColumns = @JoinColumn(name = "expression_seq")
    )
    @Builder.Default
    private Set<Note> notes = new HashSet<>();

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;
}
