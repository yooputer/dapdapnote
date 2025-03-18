package org.dapdapnote.service;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.answer.CreateAnswerRequest;
import org.dapdapnote.entity.AnswerEntity;
import org.dapdapnote.entity.QuestionEntity;
import org.dapdapnote.entity.UserEntity;
import org.dapdapnote.repository.AnswerRepository;
import org.dapdapnote.repository.QuestionRepository;
import org.dapdapnote.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public Long createAnswer(CreateAnswerRequest request) {
        UserEntity user = userRepository.findByUserId("yoojin027")
                .orElse(UserEntity.builder()
                        .userId("yoojin027")
                        .name("김유진")
                        .password("12345678")
                        .build());

        if (user.getSeq() == null) {
            userRepository.save(user);
        }

        QuestionEntity question = questionRepository.findById(request.getQuestionSeq())
                        .orElseThrow();

        AnswerEntity answer = AnswerEntity.builder()
                .content(request.getContent())
                .writer(user)
                .question(question)
                .build();

        answerRepository.save(answer);

        return answer.getSeq();
    }

}
