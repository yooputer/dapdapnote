package org.dapdapnote.repository;


import org.dapdapnote.dto.DdNoteDto;
import org.dapdapnote.entity.DdNoteEntity;
import org.dapdapnote.entity.GroupEntity;
import org.dapdapnote.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DdNoteRepository extends JpaRepository<DdNoteEntity, Long> {

    List<DdNoteEntity> findAllByWriter(UserEntity writer);
    List<DdNoteEntity> findAllByGroup(GroupEntity group);
}
