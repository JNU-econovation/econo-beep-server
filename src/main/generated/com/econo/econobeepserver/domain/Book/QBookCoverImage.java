package com.econo.econobeepserver.domain.Book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookCoverImage is a Querydsl query type for BookCoverImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookCoverImage extends EntityPathBase<BookCoverImage> {

    private static final long serialVersionUID = -75346532L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookCoverImage bookCoverImage = new QBookCoverImage("bookCoverImage");

    public final QBook book;

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBookCoverImage(String variable) {
        this(BookCoverImage.class, forVariable(variable), INITS);
    }

    public QBookCoverImage(Path<? extends BookCoverImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookCoverImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookCoverImage(PathMetadata metadata, PathInits inits) {
        this(BookCoverImage.class, metadata, inits);
    }

    public QBookCoverImage(Class<? extends BookCoverImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book"), inits.get("book")) : null;
    }

}

