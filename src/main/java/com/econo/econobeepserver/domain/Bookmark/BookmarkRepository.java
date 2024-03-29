package com.econo.econobeepserver.domain.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUser_IdAndRentee_Id(Long userId, Long renteeId);

    List<Bookmark> findByUser_Id(Long userId);

    Integer countByRentee_Id(Long renteeId);

    Boolean existsByUser_IdAndRentee_Id(Long userId, Long renteeId);
}
