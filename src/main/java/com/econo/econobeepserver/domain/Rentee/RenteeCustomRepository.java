package com.econo.econobeepserver.domain.Rentee;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RenteeCustomRepository {

    Long countByNameContainingFromBook(String name);
    List<Rentee> findRenteesByNameContainingFromBookWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromBookOrderByCreatedAsc(String name);
    List<Rentee> findRenteesByNameContainingFromBookOrderByCreatedAscWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromBookOrderByCreatedDesc(String name);
    List<Rentee> findRenteesByNameContainingFromBookOrderByCreatedDescWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromBookOrderByLatestRental(String name);
    List<Rentee> findRenteesByNameContainingFromBookOrderByLatestRentalWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromBookOrderByOutdatedRental(String name);
    List<Rentee> findRenteesByNameContainingFromBookOrderByOutdatedRentalWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromDevice(String name);
    List<Rentee> findRenteesByNameContainingFromDeviceWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromDeviceOrderByCreatedAsc(String name);
    List<Rentee> findRenteesByNameContainingFromDeviceOrderByCreatedAscWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromDeviceOrderByCreatedDesc(String name);
    List<Rentee> findRenteesByNameContainingFromDeviceOrderByCreatedDescWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromDeviceOrderByLatestRental(String name);
    List<Rentee> findRenteesByNameContainingFromDeviceOrderByLatestRentalWithPaging(String name, Pageable pageable);

    Long countByNameContainingFromDeviceOrderByOutdatedRental(String name);
    List<Rentee> findRenteesByNameContainingFromDeviceOrderByOutdatedRentalWithPaging(String name, Pageable pageable);
}