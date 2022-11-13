package com.econo.econobeepserver.domain.Rentee;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static com.econo.econobeepserver.domain.Rentee.QRentee.rentee;
import static com.econo.econobeepserver.domain.Rental.QRental.rental;

@RequiredArgsConstructor
public class RenteeCustomRepositoryImpl implements RenteeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Rentee> findRenteesNameContainingFromBookWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(name)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

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
        List<Long> latestRentedBookIds =
                jpaQueryFactory
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
                        .fetch();

        List<Rentee> rentees = new ArrayList<>();
        for (Long latestRentedBookId : latestRentedBookIds) {
            rentees.add(
                    jpaQueryFactory
                            .select(rentee)
                            .from(rentee)
                            .where(rentee.id.eq(latestRentedBookId))
                            .fetchOne()
            );
        }

        return rentees;
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromBookOrderByOutdatedRentalWithPaging(String name, Pageable pageable) {
        List<Long> outdatedRentedBookIds =
                jpaQueryFactory
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
                        .fetch();

        List<Rentee> rentees = new ArrayList<>();
        for (Long outdatedRentedBookId : outdatedRentedBookIds) {
            rentees.add(
                    jpaQueryFactory
                            .select(rentee)
                            .from(rentee)
                            .where(rentee.id.eq(outdatedRentedBookId))
                            .fetchOne()
            );
        }

        return rentees;
    }


    @Override
    public List<Rentee> findRenteesNameContainingFromDeviceWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
                        rentee.name.contains(name)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromDeviceOrderByCreatedAscWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
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
    public List<Rentee> findRenteesNameContainingFromDeviceOrderByCreatedDescWithPaging(String name, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
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
    public List<Rentee> findRenteesNameContainingFromDeviceOrderByLatestRentalWithPaging(String name, Pageable pageable) {
        List<Long> latestRentedDeviceIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(
                                rental.rentee.type.eq(RenteeType.DEVICE),
                                rental.rentee.name.contains(name)
                        )
                        .orderBy(
                                rental.rentalDateTime.desc()
                        )
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        List<Rentee> rentees = new ArrayList<>();
        for (Long latestRentedDeviceId : latestRentedDeviceIds) {
            rentees.add(
                    jpaQueryFactory
                            .select(rentee)
                            .from(rentee)
                            .where(rentee.id.eq(latestRentedDeviceId))
                            .fetchOne()
            );
        }

        return rentees;
    }

    @Override
    public List<Rentee> findRenteesNameContainingFromDeviceOrderByOutdatedRentalWithPaging(String name, Pageable pageable) {
        List<Long> outdatedRentedDeviceIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(
                                rental.rentee.type.eq(RenteeType.DEVICE),
                                rental.rentee.name.contains(name)
                        )
                        .orderBy(
                                rental.rentalDateTime.asc()
                        )
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        List<Rentee> rentees = new ArrayList<>();
        for (Long outdatedRentedDeviceId : outdatedRentedDeviceIds) {
            rentees.add(
                    jpaQueryFactory
                            .select(rentee)
                            .from(rentee)
                            .where(rentee.id.eq(outdatedRentedDeviceId))
                            .fetchOne()
            );
        }

        return rentees;
    }
}
