package com.econo.econobeepserver.domain.Rentee;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RenteeCustomRepository {

    Long countByRenteeTypeAndRenteeNameContaining(RenteeType renteeType, String renteeName);
    List<Rentee> findByRenteeTypeAndRenteeNameContainingWithPaging(RenteeType renteeType, String renteeName, Pageable pageable);

    Long countByRenteeTypeAndRenteeNameContainingOrderByCreatedAsc(RenteeType renteeType, String renteeName);
    List<Rentee> findByRenteeTypeAndRenteeNameContainingOrderByCreatedAscWithPaging(RenteeType renteeType, String renteeName, Pageable pageable);

    Long countByRenteeTypeAndRenteeNameContainingOrderByCreatedDesc(RenteeType renteeType, String renteeName);
    List<Rentee> findByRenteeTypeAndRenteeNameContainingOrderByCreatedDescWithPaging(RenteeType renteeType, String renteeName, Pageable pageable);

    Long countByRenteeTypeAndRenteeNameContainingOrderByLatestRental(RenteeType renteeType, String renteeName);
    List<Rentee> findByRenteeTypeAndRenteeNameContainingOrderByLatestRentalWithPaging(RenteeType renteeType, String renteeName, Pageable pageable);

    Long countByRenteeTypeAndRenteeNameContainingOrderByOutdatedRental(RenteeType renteeType, String renteeName);
    List<Rentee> findByRenteeTypeAndRenteeNameContainingOrderByOutdatedRentalWithPaging(RenteeType renteeType, String renteeName, Pageable pageable);
}