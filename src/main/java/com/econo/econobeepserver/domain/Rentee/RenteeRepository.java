package com.econo.econobeepserver.domain.Rentee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RenteeRepository extends JpaRepository<Rentee, Long>, RenteeCustomRepository {

}
