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
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false)
    private String korean;

    @Column(nullable = false)
    private String english;

    @ManyToOne
    @JoinColumn(name = "writer_seq", nullable = false)
    private User writer;

    @ManyToMany(mappedBy = "notes", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Expression> expressions = new HashSet<>();

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;
}