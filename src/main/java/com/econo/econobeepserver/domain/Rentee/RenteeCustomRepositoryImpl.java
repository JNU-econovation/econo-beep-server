package com.econo.econobeepserver.domain.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.econo.econobeepserver.domain.Rental.QRental.rental;
import static com.econo.econobeepserver.domain.Rentee.QRentee.rentee;


@RequiredArgsConstructor
public class RenteeCustomRepositoryImpl implements RenteeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private BooleanExpression gtRenteeId(Long renteeId) {
        if (renteeId == null) {
            return null;
        }

        return rentee.id.gt(renteeId);
    }

    private BooleanExpression ltRenteeId(Long renteeId) {
        if (renteeId == null) {
            return null;
        }

        return rentee.id.lt(renteeId);
    }

    private BooleanExpression compareRenteeId(Long renteeId, Boolean isIdAsc, Boolean isIdDesc) {
        if (isIdAsc != null && isIdAsc) {
            return gtRenteeId(renteeId);

        } else {
            return ltRenteeId(renteeId);
        }
    }

    private OrderSpecifier sortRenteeId(Boolean isIdAsc, Boolean isIdDesc) {
        if (isIdAsc != null && isIdAsc) {
            return rentee.id.asc();

        } else {
            return rentee.id.desc();
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
    public List<Rentee> searchRenteeWithPaging(String keyword, int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        ltRenteeId(lastId),
                        rentee.title.contains(keyword)
                )
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeEqualWithPaging(RenteeType renteeType, String keyword, int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        ltRenteeId(lastId),
                        rentee.type.eq(renteeType),
                        rentee.title.contains(keyword)
                )
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeEqualByIdSortWithPaging(RenteeType renteeType, String keyword, int pageSize, Long lastId, Boolean isIdAsc, Boolean isIdDesc) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        compareRenteeId(lastId, isIdAsc, isIdDesc),
                        rentee.type.eq(renteeType),
                        rentee.title.contains(keyword)
                )
                .orderBy(sortRenteeId(isIdAsc, isIdDesc))
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeEqualByRecentRentDescWithPaging(RenteeType renteeType, String keyword, int pageSize, long offset) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(rentee.id.in(
                        JPAExpressions
                                .select(rental.rentee.id)
                                .from(rental)
                                .where(
                                        rental.rentee.type.eq(renteeType),
                                        rental.rentee.title.contains(keyword)g
                                )
                                .orderBy(rental.rentalDateTime.desc())
                                .offset(offset)
                                .limit(pageSize)
                ))
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeNotEqualWithPaging(RenteeType renteeType, String keyword, int pageSize, Long lastId) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        ltRenteeId(lastId),
                        rentee.type.ne(renteeType),
                        rentee.title.contains(keyword)
                )
                .orderBy(rentee.id.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeNotEqualByIdSortPaging(RenteeType renteeType, String keyword, int pageSize, Long lastId, Boolean isIdAsc, Boolean isIdDesc) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        compareRenteeId(lastId, isIdAsc, isIdDesc),
                        rentee.type.ne(renteeType),
                        rentee.title.contains(keyword)
                )
                .orderBy(sortRenteeId(isIdAsc, isIdDesc))
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<Rentee> searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPaging(RenteeType renteeType, String keyword, int pageSize, long offset) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(rentee.id.in(
                        JPAExpressions
                                .select(rental.rentee.id)
                                .from(rental)
                                .where(
                                        rental.rentee.type.ne(renteeType),
                                        rental.rentee.title.contains(keyword)
                                )
                                .orderBy(rental.rentalDateTime.desc())
                                .offset(offset)
                                .limit(pageSize)
                ))
                .fetch();
    }
}
