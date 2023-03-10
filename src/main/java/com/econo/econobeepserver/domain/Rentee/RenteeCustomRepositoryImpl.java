package com.econo.econobeepserver.domain.Rentee;

import com.querydsl.core.types.Predicate;
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

    private Predicate[] getDynamicWhereForRenteeByRenteeTypeAndRenteeName(RenteeType renteeType, String renteeName) {
        if (RenteeType.ANY.equals(renteeType)) {
            return new Predicate[] {
                    rentee.name.contains(renteeName)
            };
        }

        return new Predicate[] {
                rentee.type.eq(renteeType),
                rentee.name.contains(renteeName)
        };
    }

    private Predicate[] getDynamicWhereForRenteeInRentalByRenteeTypeAndRenteeName(RenteeType renteeType, String renteeName) {
        if (RenteeType.ANY.equals(renteeType)) {
            return new Predicate[] {
                    rental.rentee.name.contains(renteeName)
            };
        }

        return new Predicate[] {
                rental.rentee.type.eq(renteeType),
                rental.rentee.name.contains(renteeName)
        };
    }

    @Override
    public Long countByRenteeTypeAndRenteeNameContaining(RenteeType renteeType, String renteeName) {

        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(getDynamicWhereForRenteeByRenteeTypeAndRenteeName(renteeType, renteeName))
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeTypeAndRenteeNameContainingWithPaging(RenteeType renteeType, String renteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(getDynamicWhereForRenteeByRenteeTypeAndRenteeName(renteeType, renteeName))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeTypeAndRenteeNameContainingOrderByCreatedAsc(RenteeType renteeType, String renteeName) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(getDynamicWhereForRenteeByRenteeTypeAndRenteeName(renteeType, renteeName))
                .orderBy(rentee.createdDate.asc())
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeTypeAndRenteeNameContainingOrderByCreatedAscWithPaging(RenteeType renteeType, String renteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(getDynamicWhereForRenteeByRenteeTypeAndRenteeName(renteeType, renteeName))
                .orderBy(rentee.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeTypeAndRenteeNameContainingOrderByCreatedDesc(RenteeType renteeType, String renteeName) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(getDynamicWhereForRenteeByRenteeTypeAndRenteeName(renteeType, renteeName))
                .orderBy(rentee.createdDate.desc())
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeTypeAndRenteeNameContainingOrderByCreatedDescWithPaging(RenteeType renteeType, String renteeName, Pageable pageable) {
        return jpaQueryFactory
                .select(rentee)
                .from(rentee)
                .where(getDynamicWhereForRenteeByRenteeTypeAndRenteeName(renteeType, renteeName))
                .orderBy(rentee.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countByRenteeTypeAndRenteeNameContainingOrderByLatestRental(RenteeType renteeType, String renteeName) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(getDynamicWhereForRenteeInRentalByRenteeTypeAndRenteeName(renteeType, renteeName))
                .distinct()
                .orderBy(rental.rentalDateTime.desc())
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeTypeAndRenteeNameContainingOrderByLatestRentalWithPaging(RenteeType renteeType, String renteeName, Pageable pageable) {
        List<Long> latestRentedBookIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(getDynamicWhereForRenteeInRentalByRenteeTypeAndRenteeName(renteeType, renteeName))
                        .distinct()
                        .orderBy(rental.rentalDateTime.desc())
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
    public Long countByRenteeTypeAndRenteeNameContainingOrderByOutdatedRental(RenteeType renteeType, String renteeName) {
        return jpaQueryFactory
                .select(rental.rentee.id)
                .from(rental)
                .where(getDynamicWhereForRenteeInRentalByRenteeTypeAndRenteeName(renteeType, renteeName))
                .distinct()
                .orderBy(rental.rentalDateTime.asc())
                .fetchCount();
    }

    @Override
    public List<Rentee> findByRenteeTypeAndRenteeNameContainingOrderByOutdatedRentalWithPaging(RenteeType renteeType, String renteeName, Pageable pageable) {
        List<Long> outdatedRentedBookIds =
                jpaQueryFactory
                        .select(rental.rentee.id)
                        .from(rental)
                        .where(getDynamicWhereForRenteeInRentalByRenteeTypeAndRenteeName(renteeType, renteeName))
                        .distinct()
                        .orderBy(rental.rentalDateTime.asc())
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
}
