package org.dapdapnote;


import org.dapdapnote.entity.DdNoteEntity;
import org.dapdapnote.entity.GroupEntity;
import org.dapdapnote.entity.UserEntity;
import org.dapdapnote.enums.DdNoteType;
import org.dapdapnote.repository.DdNoteRepository;
import org.dapdapnote.repository.GroupRepository;
import org.dapdapnote.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class JpaTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DdNoteRepository ddNoteRepository;

    @Test
    public void testCreateUserAndGroups() {
        UserEntity user = userRepository.findByUserId("yoojin027")
                .orElse(UserEntity.builder()
                        .userId("yoojin027")
                        .name("김유진")
                        .password("12345678")
                        .build()
                );

        if (user.getSeq() == null) {
            userRepository.save(user);
        }

        GroupEntity group1 = groupRepository.findByName("group1")
                .orElse(GroupEntity.builder()
                        .name("group1")
                        .password("1111")
                        .description("테스트~")
                        .build());
        if (group1.getSeq() == null) {
            groupRepository.save(group1);
        }

        GroupEntity group2 = groupRepository.findByName("group2")
                .orElse(GroupEntity.builder()
                        .name("group2")
                        .password("2222")
                        .description("테스트~")
                        .build());
        if (group2.getSeq() == null) {
            groupRepository.save(group2);
        }

        user.getGroups().add(group1);
        user.getGroups().add(group2);
        userRepository.save(user);
    }

    @Test
    public void testCreateDdNotes() {
        UserEntity user = userRepository.findByUserId("yoojin027").orElseThrow();

        DdNoteEntity question1 = DdNoteEntity.builder()
                .type(DdNoteType.Q)
                .content("겁먹다")
                .writer(user)
                .build();
        ddNoteRepository.save(question1);

        DdNoteEntity answer1 = DdNoteEntity.builder()
                .type(DdNoteType.A)
                .content("get scared")
                .writer(user)
                .parentDdNote(question1)
                .build();
        ddNoteRepository.save(answer1);
    }
}
