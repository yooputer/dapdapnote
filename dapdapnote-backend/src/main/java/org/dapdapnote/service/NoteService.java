package org.dapdapnote.service;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.expression.SaveExpressionRequest;
import org.dapdapnote.dto.note.NoteListDto;
import org.dapdapnote.dto.note.SaveNoteRequest;
import org.dapdapnote.dto.note.NoteDto;
import org.dapdapnote.entity.Expression;
import org.dapdapnote.entity.Note;
import org.dapdapnote.entity.User;
import org.dapdapnote.repository.ExpressionRepository;
import org.dapdapnote.repository.NoteRepository;
import org.dapdapnote.repository.UserRepository;
import org.dapdapnote.utils.ListUtil;
import org.dapdapnote.utils.SetUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final ExpressionRepository expressionRepository;

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
    public List<NoteListDto> getList() {
        return noteRepository.findAll().stream()
                .map(NoteListDto::new)
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

        Note note;
        if (request.getSeq() == null) {
            note = createNote(request);
        }else {
            note = updateNote(request);
        }

        return note.getSeq();
    }

    private Note createNote(SaveNoteRequest request) {
        Note note = Note.builder()
                .korean(request.getKorean())
                .english(request.getEnglish())
                .writer(request.getWriter())
                .status(request.getStatus())
                .build();
        noteRepository.save(note);

        List<Expression> expressionList = saveExpressionList(request, note);

        note.setExpressions(SetUtil.objectListToSet(expressionList));
        note.setExpressionCnt(note.getExpressions().size());
        noteRepository.save(note);

        return note;
    }

    private Note updateNote(SaveNoteRequest request) {
        if (request.getSeq() == null) {
            throw new RuntimeException("note seq is null");
        }

        Note note = noteRepository.findById(request.getSeq())
                .orElseThrow(() -> new RuntimeException("note not found. seq=" + request.getSeq()));

        List<Expression> expressionList = saveExpressionList(request, note);

        note.setKorean(request.getKorean());
        note.setEnglish(request.getEnglish());
        note.setExpressions(SetUtil.objectListToSet(expressionList));
        note.setExpressionCnt(note.getExpressions().size());
        note.setStatus(request.getStatus());
        noteRepository.save(note);

        return note;
    }

    /**
     * Expression List 저장
     * @param request
     * @param note
     * @return List<Expression>
     */
    private List<Expression> saveExpressionList(SaveNoteRequest request, Note note) {
        List<Expression> expressionList = new ArrayList<>();

        for (SaveExpressionRequest expressionRequest : request.getExpressionList()) {
            expressionRequest.setWriter(request.getWriter());
            expressionRequest.setNote(note);

            Expression expression;
            if (expressionRequest.getSeq() == null) {
                expression = createExpression(expressionRequest);
            }else{
                expression = updateExpression(expressionRequest);
            }

            expressionList.add(expression);
        }

        return expressionList;
    }

    private Expression createExpression(SaveExpressionRequest expressionRequest) {
        Expression expression = Expression.builder()
                .korean(expressionRequest.getKorean())
                .englishList(ListUtil.listToJson(expressionRequest.getEnglishList()))
                .writer(expressionRequest.getWriter())
                .notes(SetUtil.objectToSet(expressionRequest.getNote()))
                .build();
        expressionRepository.save(expression);

        return expression;
    }

    private Expression updateExpression(SaveExpressionRequest expressionRequest) {
        if (expressionRequest.getSeq() == null) {
            throw new RuntimeException("expression seq is null");
        }

        Expression expression = expressionRepository.findById(expressionRequest.getSeq())
                .orElseThrow(() -> new RuntimeException("expression not found. seq=" + expressionRequest.getSeq()));

        expression.setKorean(expressionRequest.getKorean());
        expression.setEnglishList(ListUtil.listToJson(expressionRequest.getEnglishList()));
        expression.getNotes().add(expressionRequest.getNote());
        expressionRepository.save(expression);

        return expression;
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