package com.econo.econobeepserver.domain.Rentee;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.econo.econobeepserver.domain.Rentee.QRentee.rentee;
import static com.econo.econobeepserver.domain.Rental.QRental.rental;

@RequiredArgsConstructor
public class RenteeCustomRepositoryImpl implements RenteeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Rentee> findRenteesNameContainingFromBookOrderByCreatedAscWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(name)
                )
                .orderBy(
                        rentee.createdDate.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromBookOrderByCreatedDescWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(name)
                )
                .orderBy(
                        rentee.createdDate.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromBookOrderByLatestRentalWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(rentee.id.in(
                        JPAExpressions
                                .select(rental.rentee.id)
                                .from(rental)
                                .where(
                                        rental.rentee.type.eq(RenteeType.BOOK),
                                        rental.rentee.name.contains(name)
                                )
                                .orderBy(
                                        rental.rentalDateTime.desc()
                                )
                                .offset(pageable.getOffset())
                                .limit(pageable.getPageSize())
                ))
                .fetch();
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromBookOrderByOutdatedRentalWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(rentee.id.in(
                        JPAExpressions
                                .select(rental.rentee.id)
                                .from(rental)
                                .where(
                                        rental.rentee.type.eq(RenteeType.BOOK),
                                        rental.rentee.name.contains(name)
                                )
                                .orderBy(
                                        rental.rentalDateTime.asc()
                                )
                                .offset(pageable.getOffset())
                                .limit(pageable.getPageSize())
                ))
                .fetch();
    }
    ////

    @Override
    public List<Rentee> findRenteesNameContainingFromEquipmentOrderByCreatedAscWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.EQUIPMENT),
                        rentee.name.contains(name)
                )
                .orderBy(
                        rentee.createdDate.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromEquipmentOrderByCreatedDescWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.EQUIPMENT),
                        rentee.name.contains(name)
                )
                .orderBy(
                        rentee.createdDate.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromEquipmentOrderByLatestRentalWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(rentee.id.in(
                        JPAExpressions
                                .select(rental.rentee.id)
                                .from(rental)
                                .where(
                                        rental.rentee.type.eq(RenteeType.EQUIPMENT),
                                        rental.rentee.name.contains(name)
                                )
                                .orderBy(
                                        rental.rentalDateTime.desc()
                                )
                                .offset(pageable.getOffset())
                                .limit(pageable.getPageSize())
                ))
                .fetch();
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromEquipmentOrderByOutdatedRentalWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(rentee.id.in(
                        JPAExpressions
                                .select(rental.rentee.id)
                                .from(rental)
                                .where(
                                        rental.rentee.type.eq(RenteeType.EQUIPMENT),
                                        rental.rentee.name.contains(name)
                                )
                                .orderBy(
                                        rental.rentalDateTime.asc()
                                )
                                .offset(pageable.getOffset())
                                .limit(pageable.getPageSize())
                ))
                .fetch();
    }
}
