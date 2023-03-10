package com.econo.econobeepserver.domain.Rentee;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RenteeCustomRepository {

    Long countByRenteeNameContainingFromBook(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromBookWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromBookOrderByCreatedAsc(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromBookOrderByCreatedAscWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromBookOrderByCreatedDesc(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromBookOrderByCreatedDescWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromBookOrderByLatestRental(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromBookOrderByLatestRentalWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromBookOrderByOutdatedRental(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromBookOrderByOutdatedRentalWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDevice(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromDeviceWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDeviceOrderByCreatedAsc(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromDeviceOrderByCreatedAscWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDeviceOrderByCreatedDesc(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromDeviceOrderByCreatedDescWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDeviceOrderByLatestRental(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromDeviceOrderByLatestRentalWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDeviceOrderByOutdatedRental(String RenteeName);
    List<Rentee> findRenteesByRenteeNameContainingFromDeviceOrderByOutdatedRentalWithPaging(String RenteeName, Pageable pageable);
}