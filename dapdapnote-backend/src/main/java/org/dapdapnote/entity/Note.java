package org.dapdapnote.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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

    @Column(nullable = true)
    private String english;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "writer_seq", nullable = false)
    private User writer;

    @ManyToMany(mappedBy = "notes", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Expression> expressions = new HashSet<>();

    @Column(nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private Integer expressionCnt = 0;

    @CreatedDate
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;
}