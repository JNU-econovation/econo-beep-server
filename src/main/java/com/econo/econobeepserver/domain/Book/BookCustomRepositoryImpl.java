package com.econo.econobeepserver.domain.Book;

import com.econo.econobeepserver.domain.RenteeType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.econo.econobeepserver.domain.Book.QBook.book;

@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    private BooleanExpression ltBookId(Long bookId) {
        if(bookId == null) {
            return null;
        }

        return book.id.lt(bookId);
    }

    @Override
    public List<Book> getRecentBookWithPaging(int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(book)
                .from(book)
                .where(ltBookId(lastId))
                .orderBy(book.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Book> getBookByTypeWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(book)
                .from(book)
                .where(
                        ltBookId(lastId),
                        book.type.eq(renteeType)
                )
                .orderBy(book.id.desc())
                .limit(pageSize)
                .fetch();
    }


    @Override
    public List<Book> searchBookByKeyword(String keyword) {
        return jpaQueryFactory
                .select(book)
                .from(book)
                .where(book.title.contains(keyword))
                .fetch();
    }
}
