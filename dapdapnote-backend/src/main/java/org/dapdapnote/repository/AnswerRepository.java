package org.dapdapnote.repository;


import org.dapdapnote.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findAllByQuestionSeq(Long questionSeq);
}
