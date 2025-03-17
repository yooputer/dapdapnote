package org.dapdapnote;


import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.question.QuestionDto;
import org.dapdapnote.entity.UserEntity;
import org.dapdapnote.repository.QuestionRepository;
import org.dapdapnote.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public List<QuestionDto> getMyQuestions() {
        UserEntity user = userRepository.findByUserId("yoojin027")
                        .orElseThrow();
        List<QuestionDto> questions = questionRepository.findAllByWriter(user).stream()
                .map(QuestionDto::new)
                .toList();

        return questions;
    }

}
