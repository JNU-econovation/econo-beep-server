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
    public Long countByNameContainingFromBook(String name) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(name)
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromBookWithPaging(String name, Pageable pageable) {
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
    public Long countByNameContainingFromBookOrderByCreatedAsc(String name) {
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
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromBookOrderByCreatedAscWithPaging(String name, Pageable pageable) {
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
    public Long countByNameContainingFromBookOrderByCreatedDesc(String name) {
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
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromBookOrderByCreatedDescWithPaging(String name, Pageable pageable) {
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
    public Long countByNameContainingFromBookOrderByLatestRental(String name) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(
                        rental.rentee.type.eq(RenteeType.BOOK),
                        rental.rentee.name.contains(name)
                )
                .distinct()
                .orderBy(
                        rental.rentalDateTime.desc()
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromBookOrderByLatestRentalWithPaging(String name, Pageable pageable) {
        List<Long> latestRentedBookIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(
                                rental.rentee.type.eq(RenteeType.BOOK),
                                rental.rentee.name.contains(name)
                        )
                        .distinct()
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
    public Long countByNameContainingFromBookOrderByOutdatedRental(String name) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(
                        rental.rentee.type.eq(RenteeType.BOOK),
                        rental.rentee.name.contains(name)
                )
                .orderBy(
                        rental.rentalDateTime.asc()
                )
                .distinct()
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromBookOrderByOutdatedRentalWithPaging(String name, Pageable pageable) {
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
                        .distinct()
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
    public Long countByNameContainingFromDevice(String name) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
                        rentee.name.contains(name)
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromDeviceWithPaging(String name, Pageable pageable) {
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
    public Long countByNameContainingFromDeviceOrderByCreatedAsc(String name) {
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
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromDeviceOrderByCreatedAscWithPaging(String name, Pageable pageable) {
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
    public Long countByNameContainingFromDeviceOrderByCreatedDesc(String name) {
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
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromDeviceOrderByCreatedDescWithPaging(String name, Pageable pageable) {
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
    public Long countByNameContainingFromDeviceOrderByLatestRental(String name) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(
                        rental.rentee.type.eq(RenteeType.DEVICE),
                        rental.rentee.name.contains(name)
                )
                .orderBy(
                        rental.rentalDateTime.desc()
                )
                .distinct()
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromDeviceOrderByLatestRentalWithPaging(String name, Pageable pageable) {
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
                        .distinct()
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
    public Long countByNameContainingFromDeviceOrderByOutdatedRental(String name) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(
                        rental.rentee.type.eq(RenteeType.DEVICE),
                        rental.rentee.name.contains(name)
                )
                .orderBy(
                        rental.rentalDateTime.asc()
                )
                .distinct()
                .fetchCount();
    }

    @Override
    public List<Rentee> findRenteesByNameContainingFromDeviceOrderByOutdatedRentalWithPaging(String name, Pageable pageable) {
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
                        .distinct()
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
