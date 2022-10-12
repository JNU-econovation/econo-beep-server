package com.econo.econobeepserver.domain.Rentee;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RenteeCustomRepository {

    List<Rentee> findRenteesNameContainingFromBookWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromBookOrderByCreatedAscWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromBookOrderByCreatedDescWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromBookOrderByLatestRentalWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromBookOrderByOutdatedRentalWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromEquipmentWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromEquipmentOrderByCreatedAscWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromEquipmentOrderByCreatedDescWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromEquipmentOrderByLatestRentalWithPaging(String name, Pageable pageable);

    List<Rentee> findRenteesNameContainingFromEquipmentOrderByOutdatedRentalWithPaging(String name, Pageable pageable);
}