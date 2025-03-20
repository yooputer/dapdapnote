package org.dapdapnote.repository;


import org.dapdapnote.entity.Expression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpressionRepository extends JpaRepository<Expression, Long> {
}
