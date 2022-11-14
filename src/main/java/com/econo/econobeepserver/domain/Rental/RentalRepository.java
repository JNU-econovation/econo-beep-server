package com.econo.econobeepserver.domain.Rental;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Optional<Rental> findFirstByRentee_IdOrderByCreatedDateDesc(long renteeId);

    List<Rental> findByRentee_Id(long renteeId);

    List<Rental> findByReturnDateTimeIsNullAndRenter_Id(long renterId);

    List<Rental> findByReturnDateTimeIsNotNullAndRenter_Id(long renterId);
}
