package org.dapdapnote.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false, unique = true)
    private String name;

    private String password;

    private String description;

    @ManyToMany(mappedBy = "groups")
    @Builder.Default
    private Set<UserEntity> users = new HashSet<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDate;

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass())
                && ((GroupEntity) obj).getName().equals(this.getName());
    }
}
