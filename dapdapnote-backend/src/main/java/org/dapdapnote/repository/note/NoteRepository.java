package org.dapdapnote.repository.note;


import org.dapdapnote.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}