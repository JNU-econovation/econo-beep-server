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
    public Long countByRenteeNameContainingFromBook(String RenteeName) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(RenteeName)
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromBookWithPaging(String RenteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(RenteeName)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeNameContainingFromBookOrderByCreatedAsc(String RenteeName) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rentee.createdDate.asc()
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromBookOrderByCreatedAscWithPaging(String RenteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rentee.createdDate.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeNameContainingFromBookOrderByCreatedDesc(String RenteeName) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rentee.createdDate.desc()
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromBookOrderByCreatedDescWithPaging(String RenteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.BOOK),
                        rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rentee.createdDate.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeNameContainingFromBookOrderByLatestRental(String RenteeName) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(
                        rental.rentee.type.eq(RenteeType.BOOK),
                        rental.rentee.name.contains(RenteeName)
                )
                .distinct()
                .orderBy(
                        rental.rentalDateTime.desc()
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromBookOrderByLatestRentalWithPaging(String RenteeName, Pageable pageable) {
        List<Long> latestRentedBookIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(
                                rental.rentee.type.eq(RenteeType.BOOK),
                                rental.rentee.name.contains(RenteeName)
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
    public Long countByRenteeNameContainingFromBookOrderByOutdatedRental(String RenteeName) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(
                        rental.rentee.type.eq(RenteeType.BOOK),
                        rental.rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rental.rentalDateTime.asc()
                )
                .distinct()
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromBookOrderByOutdatedRentalWithPaging(String RenteeName, Pageable pageable) {
        List<Long> outdatedRentedBookIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(
                                rental.rentee.type.eq(RenteeType.BOOK),
                                rental.rentee.name.contains(RenteeName)
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
    public Long countByRenteeNameContainingFromDevice(String RenteeName) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
                        rentee.name.contains(RenteeName)
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromDeviceWithPaging(String RenteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
                        rentee.name.contains(RenteeName)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeNameContainingFromDeviceOrderByCreatedAsc(String RenteeName) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
                        rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rentee.createdDate.asc()
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromDeviceOrderByCreatedAscWithPaging(String RenteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
                        rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rentee.createdDate.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeNameContainingFromDeviceOrderByCreatedDesc(String RenteeName) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
                        rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rentee.createdDate.desc()
                )
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromDeviceOrderByCreatedDescWithPaging(String RenteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(
                        rentee.type.eq(RenteeType.DEVICE),
                        rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rentee.createdDate.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeNameContainingFromDeviceOrderByLatestRental(String RenteeName) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(
                        rental.rentee.type.eq(RenteeType.DEVICE),
                        rental.rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rental.rentalDateTime.desc()
                )
                .distinct()
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromDeviceOrderByLatestRentalWithPaging(String RenteeName, Pageable pageable) {
        List<Long> latestRentedDeviceIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(
                                rental.rentee.type.eq(RenteeType.DEVICE),
                                rental.rentee.name.contains(RenteeName)
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
    public Long countByRenteeNameContainingFromDeviceOrderByOutdatedRental(String RenteeName) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(
                        rental.rentee.type.eq(RenteeType.DEVICE),
                        rental.rentee.name.contains(RenteeName)
                )
                .orderBy(
                        rental.rentalDateTime.asc()
                )
                .distinct()
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeNameContainingFromDeviceOrderByOutdatedRentalWithPaging(String RenteeName, Pageable pageable) {
        List<Long> outdatedRentedDeviceIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(
                                rental.rentee.type.eq(RenteeType.DEVICE),
                                rental.rentee.name.contains(RenteeName)
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
