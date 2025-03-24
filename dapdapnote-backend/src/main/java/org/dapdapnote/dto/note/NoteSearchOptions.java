package org.dapdapnote.dto.note;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteSearchOptions {
    private boolean onlyUnsolved;
    private boolean onlyToday;
    private String searchType;
    private String searchKeyword;
}