package org.dapdapnote.service;


import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.answer.AnswerDto;
import org.dapdapnote.dto.question.CreateQuestionRequest;
import org.dapdapnote.dto.question.QuestionDto;
import org.dapdapnote.entity.AnswerEntity;
import org.dapdapnote.entity.QuestionEntity;
import org.dapdapnote.entity.UserEntity;
import org.dapdapnote.repository.AnswerRepository;
import org.dapdapnote.repository.QuestionRepository;
import org.dapdapnote.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public List<QuestionDto> getMyQuestions() {
        UserEntity user = userRepository.findByUserId("yoojin027")
                        .orElse(UserEntity.builder()
                                .userId("yoojin027")
                                .name("김유진")
                                .password("12345678")
                                .build());

        if (user.getSeq() == null) {
            userRepository.save(user);
        }
        List<QuestionDto> questions = questionRepository.findAllByWriter(user).stream()
                .map(QuestionDto::new)
                .toList();

        for (QuestionDto question : questions) {
            List<AnswerDto> answers = answerRepository.findAllByQuestionSeq(question.getSeq())
                    .stream()
                    .map(AnswerDto::new)
                    .toList();

            question.setAnswerList(answers);
        }

        return questions;
    }

    public Long createQuestion(CreateQuestionRequest request) {
        UserEntity user = userRepository.findByUserId("yoojin027")
                .orElse(UserEntity.builder()
                        .userId("yoojin027")
                        .name("김유진")
                        .password("12345678")
                        .build());

        if (user.getSeq() == null) {
            userRepository.save(user);
        }

        QuestionEntity question = QuestionEntity.builder()
                .content(request.getContent())
                .writer(user)
                .build();

        questionRepository.save(question);

        return question.getSeq();
    }
}
