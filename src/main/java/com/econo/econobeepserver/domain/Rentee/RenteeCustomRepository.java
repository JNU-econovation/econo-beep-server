package com.econo.econobeepserver.domain.Rentee;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RenteeCustomRepository {

    List<Rentee> findRenteesNameContainingFromBookWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromBookOrderByCreatedAscWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromBookOrderByCreatedDescWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromBookOrderByLatestRentalWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromBookOrderByOutdatedRentalWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromDeviceWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromDeviceOrderByCreatedAscWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromDeviceOrderByCreatedDescWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromDeviceOrderByLatestRentalWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromDeviceOrderByOutdatedRentalWithPaging(String name, Pageable pageable);
}