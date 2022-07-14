package com.econo.econobeepserver.domain.Book;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -1580077160L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBook book = new QBook("book");

    public final StringPath authorName = createString("authorName");

    public final QBookCoverImage bookCoverImage;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath note = createString("note");

    public final DatePath<java.time.LocalDate> publishedDate = createDate("publishedDate", java.time.LocalDate.class);

    public final StringPath publisherName = createString("publisherName");

    public final ListPath<com.econo.econobeepserver.domain.BookRental.BookRental, com.econo.econobeepserver.domain.BookRental.QBookRental> rentalHistories = this.<com.econo.econobeepserver.domain.BookRental.BookRental, com.econo.econobeepserver.domain.BookRental.QBookRental>createList("rentalHistories", com.econo.econobeepserver.domain.BookRental.BookRental.class, com.econo.econobeepserver.domain.BookRental.QBookRental.class, PathInits.DIRECT2);

    public final NumberPath<Integer> rentCount = createNumber("rentCount", Integer.class);

    public final EnumPath<com.econo.econobeepserver.domain.RentState> rentState = createEnum("rentState", com.econo.econobeepserver.domain.RentState.class);

    public final StringPath title = createString("title");

    public final EnumPath<com.econo.econobeepserver.domain.RenteeType> type = createEnum("type", com.econo.econobeepserver.domain.RenteeType.class);

    public QBook(String variable) {
        this(Book.class, forVariable(variable), INITS);
    }

    public QBook(Path<? extends Book> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBook(PathMetadata metadata, PathInits inits) {
        this(Book.class, metadata, inits);
    }

    public QBook(Class<? extends Book> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bookCoverImage = inits.isInitialized("bookCoverImage") ? new QBookCoverImage(forProperty("bookCoverImage"), inits.get("bookCoverImage")) : null;
    }

}

