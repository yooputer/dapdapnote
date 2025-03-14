package org.dapdapnote.repository;


import org.dapdapnote.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    Optional<GroupEntity> findByName(String name);
}
