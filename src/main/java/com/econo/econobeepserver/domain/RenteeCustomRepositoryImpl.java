package com.econo.econobeepserver.domain;

import com.econo.econobeepserver.domain.Book.Book;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RenteeCustomRepositoryImpl implements RenteeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private BooleanExpression ltRenteeId(Long renteeId) {
        if(renteeId == null) {
            return null;
        }

//        return
    }

    @Override
    public List<Book> searchRenteeByKeyword(String keyword) {
        return null;
    }
}
