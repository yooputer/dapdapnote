package org.dapdapnote.repository.note;

import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.note.NoteSearchOptions;
import org.dapdapnote.entity.Note;
import org.dapdapnote.entity.QNote;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoteRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<Note> searchAll(NoteSearchOptions searchOptions) {
        QNote note = QNote.note;

        JPAQuery<Note> result = queryFactory
                .selectFrom(note);

        if (searchOptions.isOnlyUnsolved()) {
            result.where(note.status.eq("UNSOLVED"));
        }

        if (searchOptions.isOnlyToday()) {
            result.where(note.regDate.between(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(LocalTime.MAX)));
        }

        if (!StringUtils.isNullOrEmpty(searchOptions.getSearchType())
                && !StringUtils.isNullOrEmpty(searchOptions.getSearchKeyword())) {
            if (searchOptions.getSearchType().equals("KOREAN")) {
                result.where(note.korean.like("%" + searchOptions.getSearchKeyword() + "%"));
            }
            if (searchOptions.getSearchType().equals("ENGLISH")) {
                result.where(note.english.like("%" + searchOptions.getSearchKeyword() + "%"));
            }
        }

        return result.fetch();
    }
}