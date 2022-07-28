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

    private BooleanExpression gtRenteeId(Long renteeId) {
        if(renteeId == null) {
            return null;
        }

        return rentee.id.gt(renteeId);
    }

    private BooleanExpression compareRenteeId(Long renteeId, Boolean isIdAsc, Boolean isIdDesc) {
        if (isIdAsc) {
            return gtRenteeId(renteeId);

        } else {
            return ltRenteeId(renteeId);
        }
    }

    @Override
    public List<Rentee> getRenteesWithPaging(int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(ltRenteeId(lastId))
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> getRenteesByTypeEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
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
    public List<Rentee> getRenteesByTypeEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId, Boolean isIdAsc, Boolean isIdDesc) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        compareRenteeId(lastId, isIdAsc, isIdDesc),
                        rentee.type.eq(renteeType)
                )
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> getRenteesByTypeNotEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId) {
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
    public List<Rentee> getRenteesByTypeNotEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId, Boolean isIdAsc, Boolean isIdDesc) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        compareRenteeId(lastId, isIdAsc, isIdDesc),
                        rentee.type.ne(renteeType)
                )
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> searchRentee(String keyword) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(rentee.title.contains(keyword))
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeEqual(RenteeType renteeType, String keyword) {
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
    public List<Rentee> searchRenteeByRenteeTypeNotEqual(RenteeType renteeType, String keyword) {
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
