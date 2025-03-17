package org.dapdapnote.repository;


import org.dapdapnote.entity.QuestionEntity;
import org.dapdapnote.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findAllByWriter(UserEntity writer);
}
