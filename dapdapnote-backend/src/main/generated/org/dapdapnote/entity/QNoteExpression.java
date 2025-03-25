package org.dapdapnote.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoteExpression is a Querydsl query type for NoteExpression
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoteExpression extends EntityPathBase<NoteExpression> {

    private static final long serialVersionUID = 734158243L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoteExpression noteExpression = new QNoteExpression("noteExpression");

    public final QExpression expression;

    public final NumberPath<Integer> expressionOrder = createNumber("expressionOrder", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final QNote note;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QNoteExpression(String variable) {
        this(NoteExpression.class, forVariable(variable), INITS);
    }

    public QNoteExpression(Path<? extends NoteExpression> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoteExpression(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoteExpression(PathMetadata metadata, PathInits inits) {
        this(NoteExpression.class, metadata, inits);
    }

    public QNoteExpression(Class<? extends NoteExpression> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.expression = inits.isInitialized("expression") ? new QExpression(forProperty("expression"), inits.get("expression")) : null;
        this.note = inits.isInitialized("note") ? new QNote(forProperty("note"), inits.get("note")) : null;
    }

}

