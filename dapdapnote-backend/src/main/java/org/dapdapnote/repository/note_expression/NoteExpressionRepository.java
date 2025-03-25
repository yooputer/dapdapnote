package org.dapdapnote.repository.note_expression;


import org.dapdapnote.entity.Expression;
import org.dapdapnote.entity.Note;
import org.dapdapnote.entity.NoteExpression;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteExpressionRepository extends JpaRepository<NoteExpression, Long> {
    Optional<NoteExpression> findByNoteAndExpression(Note note, Expression expression);
}