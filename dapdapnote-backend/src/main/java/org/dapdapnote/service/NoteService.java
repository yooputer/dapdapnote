package org.dapdapnote.service;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.expression.SaveExpressionRequest;
import org.dapdapnote.dto.note.NoteSearchOptions;
import org.dapdapnote.dto.note.NoteListDto;
import org.dapdapnote.dto.note.SaveNoteRequest;
import org.dapdapnote.dto.note.NoteDto;
import org.dapdapnote.entity.Expression;
import org.dapdapnote.entity.Note;
import org.dapdapnote.entity.NoteExpression;
import org.dapdapnote.entity.User;
import org.dapdapnote.repository.expression.ExpressionRepository;
import org.dapdapnote.repository.note.NoteRepository;
import org.dapdapnote.repository.UserRepository;
import org.dapdapnote.repository.note.NoteRepositoryCustom;
import org.dapdapnote.repository.note_expression.NoteExpressionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final NoteRepositoryCustom noteRepositoryCustom;
    private final ExpressionRepository expressionRepository;
    private final NoteExpressionRepository noteExpressionRepository;

    /**
     * 테스트유저 조회
     * (존재하지 않으면 생성 후 반환)
     */
    private User getTestUser() {
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

        return user;
    }

    /**
     * 노트 목록 조회
     * @return List<NoteListDto>
     */
    public List<NoteListDto> getList(NoteSearchOptions searchOptions) {
        return noteRepositoryCustom.searchAll(searchOptions)
                .stream().map(NoteListDto::new)
                .toList();
    }

    /**
     * Note 저장
     * @param request
     * @return noteSeq
     */
    public Long saveNote(SaveNoteRequest request) {
        User user = getTestUser();
        request.setWriter(user);

        Note note = request.toEntity(user);
        noteRepository.save(note);

        note.setNoteExpressions(saveExpressions(request, note));
        note.setExpressionCnt(note.getNoteExpressions().size());
        noteRepository.save(note);

        return note.getSeq();
    }

    /**
     * 표현 저장
     * @param request
     * @param note
     * @return List<Expression>
     */
    private Set<NoteExpression> saveExpressions(SaveNoteRequest request, Note note) {
        Set<NoteExpression> noteExpressions = new HashSet<>();

        int index = 1;
        for (SaveExpressionRequest expressionRequest : request.getExpressionList()) {
            Expression expression = expressionRequest.toEntity(note);
            expressionRepository.save(expression);

            NoteExpression noteExpression = noteExpressionRepository.findByNoteAndExpression(note, expression)
                    .orElse(new NoteExpression(note, expression));
            noteExpression.setExpressionOrder(index++);

            noteExpressionRepository.save(noteExpression);

            noteExpressions.add(noteExpression);
        }

        return noteExpressions;
    }

    /**
     * 노트 수정 정보 조회
     * @param noteSeq
     * @return EditNoteResponse
     */
    public NoteDto getNoteForEdit(Long noteSeq) {
        Note note = noteRepository.findById(noteSeq)
                .orElseThrow(() -> new RuntimeException("note not found. seq=" + noteSeq));

        return new NoteDto(note);
    }
}