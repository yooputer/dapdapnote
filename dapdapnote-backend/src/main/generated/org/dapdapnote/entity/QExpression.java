package org.dapdapnote.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExpression is a Querydsl query type for Expression
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExpression extends EntityPathBase<Expression> {

    private static final long serialVersionUID = -1981662063L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExpression expression = new QExpression("expression");

    public final StringPath englishList = createString("englishList");

    public final StringPath korean = createString("korean");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final SetPath<NoteExpression, QNoteExpression> noteExpressions = this.<NoteExpression, QNoteExpression>createSet("noteExpressions", NoteExpression.class, QNoteExpression.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final QUser writer;

    public QExpression(String variable) {
        this(Expression.class, forVariable(variable), INITS);
    }

    public QExpression(Path<? extends Expression> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExpression(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExpression(PathMetadata metadata, PathInits inits) {
        this(Expression.class, metadata, inits);
    }

    public QExpression(Class<? extends Expression> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.writer = inits.isInitialized("writer") ? new QUser(forProperty("writer")) : null;
    }

}

