package com.econo.econobeepserver.domain.Rentee;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RenteeCustomRepository {

    Long countByRenteeNameContainingFromBook(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromBookWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromBookOrderByCreatedAsc(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromBookOrderByCreatedAscWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromBookOrderByCreatedDesc(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromBookOrderByCreatedDescWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromBookOrderByLatestRental(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromBookOrderByLatestRentalWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromBookOrderByOutdatedRental(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromBookOrderByOutdatedRentalWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDevice(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromDeviceWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDeviceOrderByCreatedAsc(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromDeviceOrderByCreatedAscWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDeviceOrderByCreatedDesc(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromDeviceOrderByCreatedDescWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDeviceOrderByLatestRental(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromDeviceOrderByLatestRentalWithPaging(String RenteeName, Pageable pageable);

    Long countByRenteeNameContainingFromDeviceOrderByOutdatedRental(String RenteeName);
    List<Rentee> findByRenteeNameContainingFromDeviceOrderByOutdatedRentalWithPaging(String RenteeName, Pageable pageable);
}