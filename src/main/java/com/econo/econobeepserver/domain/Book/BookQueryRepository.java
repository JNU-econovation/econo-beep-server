package com.econo.econobeepserver.domain.Book;

import com.econo.econobeepserver.domain.RenteeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookQueryRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public BookQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Book.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public List<Book> getBookWithPaging(int pageSize, Long lastId) {
        return null;
    }

    public List<Book> getBookByTypeWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        return null;
    }

    public List<Book> searchBookByKeyword(String keyword) {
        return null;
    }

    public List<String> getSearchSuggestionsByKeyword(String keyword) {
        return null;
    }

    public List<Book> getBookByIdDescWithPaging(int pageSize, Long lastId) {
        return null;
    }
}
