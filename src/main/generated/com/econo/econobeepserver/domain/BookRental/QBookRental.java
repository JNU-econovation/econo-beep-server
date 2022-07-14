package com.econo.econobeepserver.domain.BookRental;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookRental is a Querydsl query type for BookRental
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookRental extends EntityPathBase<BookRental> {

    private static final long serialVersionUID = 1267854936L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookRental bookRental = new QBookRental("bookRental");

    public final com.econo.econobeepserver.domain.Book.QBook book;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> rental_datetime = createDateTime("rental_datetime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> return_datetime = createDateTime("return_datetime", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QBookRental(String variable) {
        this(BookRental.class, forVariable(variable), INITS);
    }

    public QBookRental(Path<? extends BookRental> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookRental(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookRental(PathMetadata metadata, PathInits inits) {
        this(BookRental.class, metadata, inits);
    }

    public QBookRental(Class<? extends BookRental> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new com.econo.econobeepserver.domain.Book.QBook(forProperty("book"), inits.get("book")) : null;
    }

}

