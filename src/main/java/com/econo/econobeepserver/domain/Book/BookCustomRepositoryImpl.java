package com.econo.econobeepserver.domain.Book;

import com.econo.econobeepserver.domain.RenteeType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookCustomRepositoryImpl implements BookCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


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
