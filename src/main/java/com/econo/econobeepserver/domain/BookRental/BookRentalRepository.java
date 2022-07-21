package com.econo.econobeepserver.domain.BookRental;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {

    Optional<BookRental> findByBook_Id(long bookId);
}
