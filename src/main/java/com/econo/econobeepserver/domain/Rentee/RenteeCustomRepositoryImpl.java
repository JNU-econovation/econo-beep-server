package com.econo.econobeepserver.domain.Rentee;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.econo.econobeepserver.domain.Rentee.QRentee.rentee;


@RequiredArgsConstructor
public class RenteeCustomRepositoryImpl implements RenteeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    private BooleanExpression ltRenteeId(Long renteeId) {
        if(renteeId == null) {
            return null;
        }

        return rentee.id.lt(renteeId);
    }

    @Override
    public List<Rentee> getRecentRenteeWithPaging(int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(ltRenteeId(lastId))
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> getRenteeByTypeEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        ltRenteeId(lastId),
                        rentee.type.eq(renteeType)
                )
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> getRenteeByTypeNotEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        ltRenteeId(lastId),
                        rentee.type.ne(renteeType)
                )
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByKeyword(String keyword) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(rentee.title.contains(keyword))
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeEqualByKeyword(RenteeType renteeType, String keyword) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(renteeType),
                        rentee.title.contains(keyword)
                )
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeNotEqualByKeyword(RenteeType renteeType, String keyword) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.ne(renteeType),
                        rentee.title.contains(keyword)
                )
                .fetch();
    }
}
