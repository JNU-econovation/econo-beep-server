package com.econo.econobeepserver.domain.Rental;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Optional<Rental> findByRentee_Id(long renteeId);
}
