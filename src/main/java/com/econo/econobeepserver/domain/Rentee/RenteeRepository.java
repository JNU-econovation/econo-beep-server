package com.econo.econobeepserver.domain.Rentee;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RenteeRepository extends JpaRepository<Rentee, Long>, RenteeCustomRepository {

    Optional<Rentee> findByName(String name);

    List<Rentee> findByNameContaining(String name, Pageable pageable);

    List<Rentee> findByTypeAndNameContaining(RenteeType renteeType, String name, Pageable pageable);
}