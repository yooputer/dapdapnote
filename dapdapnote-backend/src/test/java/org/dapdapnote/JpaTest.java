package org.dapdapnote;

import org.dapdapnote.entity.Expression;
import org.dapdapnote.entity.Note;
import org.dapdapnote.entity.User;
import org.dapdapnote.repository.ExpressionRepository;
import org.dapdapnote.repository.NoteRepository;
import org.dapdapnote.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
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
    private NoteRepository noteRepository;

    @Autowired
    private ExpressionRepository expressionRepository;

    @Test
    public void testCreateUser() {
        User user = userRepository.findByUserId("yoojin027")
                .orElse(User.builder()
                        .userId("yoojin027")
                        .name("김유진")
                        .password("12345678")
                        .build()
                );

        if (user.getSeq() == null) {
            userRepository.save(user);
        }
    }

    @Test
    public void testCreateNote(){
        testCreateUser();
        User user = userRepository.findByUserId("yoojin027").orElseThrow();

        Note note = Note.builder()
                .korean("내가 표현하고 싶은 문장")
                .english("The sentence I want to say in English")
                .writer(user)
                .build();

        noteRepository.save(note);

        Expression expression1 = Expression.builder()
                .korean("문장")
                .englishList("[\"sentence\"]")
                .writer(user)
                .notes(Sets.newSet(note))
                .build();
        expressionRepository.save(expression1);

        Expression expression2 = Expression.builder()
                .korean("영어로 말하다")
                .englishList("[\"say in English\"]")
                .writer(user)
                .notes(Sets.newSet(note))
                .build();
        expressionRepository.save(expression2);

        Long noteSeq = note.getSeq();

        note.setExpressions(Sets.newSet(expression1, expression2));
        noteRepository.save(note);

        Note savedNote = noteRepository.findById(noteSeq).orElseThrow();

        assertThat(savedNote.getExpressions().size()).isEqualTo(2);
    }
}
