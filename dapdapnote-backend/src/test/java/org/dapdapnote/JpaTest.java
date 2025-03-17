package org.dapdapnote;


import org.dapdapnote.entity.AnswerEntity;
import org.dapdapnote.entity.GroupEntity;
import org.dapdapnote.entity.QuestionEntity;
import org.dapdapnote.entity.UserEntity;
import org.dapdapnote.repository.AnswerRepository;
import org.dapdapnote.repository.GroupRepository;
import org.dapdapnote.repository.QuestionRepository;
import org.dapdapnote.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

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
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

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
    public void testCreateQuestionAndAnswer() {
        UserEntity user = userRepository.findByUserId("yoojin027")
                .orElseThrow();

        QuestionEntity question1 = QuestionEntity.builder()
                .content("겁을 먹다")
                .writer(user)
                .build();
        questionRepository.save(question1);

        AnswerEntity answer1 = AnswerEntity.builder()
                .content("get scared")
                .writer(user)
                .question(question1)
                .build();
        answerRepository.save(answer1);

        AnswerEntity answer2 = AnswerEntity.builder()
                .content("get afraid")
                .writer(user)
                .question(question1)
                .build();
        answerRepository.save(answer2);


        QuestionEntity question2 = questionRepository.findById(question1.getSeq())
                .orElseThrow();
        question2.getAnswers().add(answer1);
        question2.getAnswers().add(answer2);
        questionRepository.save(question2);
    }

    @Test
    public void findAll() {
        List<QuestionEntity> questions = questionRepository.findAll();
        questions.forEach(q -> {
            System.out.println("질문 : " + q.getContent());
            q.getAnswers().forEach(a ->{
                System.out.println("답변 : " + a.getContent());
            });

            System.out.println();
        });
    }
}
