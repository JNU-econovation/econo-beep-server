package com.econo.econobeepserver.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByIdpId(Long idpId);

    Optional<User> findByIdpId(Long idpId);

    Optional<User> findByEmail(String email);
}
