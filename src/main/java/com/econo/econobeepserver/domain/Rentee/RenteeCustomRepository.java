package com.econo.econobeepserver.domain.Rentee;

import java.util.List;

public interface RenteeCustomRepository {

    List<Rentee> getRenteesWithPaging(int pageSize, Long lastId);

    List<Rentee> searchRenteeWithPaging(String keyword, int pageSize, Long lastId);

    List<Rentee> searchRenteeByRenteeTypeEqualWithPaging(RenteeType renteeType, String keyword, int pageSize, Long lastId);

    List<Rentee> searchRenteeByRenteeTypeEqualByIdSortWithPaging(RenteeType renteeType, String keyword, int pageSize, Long lastId, Boolean isIdAsc, Boolean isIdDesc);

    List<Rentee> searchRenteeByRenteeTypeEqualByRecentRentDescWithPaging(RenteeType renteeType, String keyword, int pageSize, long offset);

    List<Rentee> searchRenteeByRenteeTypeNotEqualWithPaging(RenteeType renteeType, String keyword, int pageSize, Long lastId);

    List<Rentee> searchRenteeByRenteeTypeNotEqualByIdSortPaging(RenteeType renteeType, String keyword, int pageSize, Long lastId, Boolean isIdAsc, Boolean isIdDesc);

    List<Rentee> searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPaging(RenteeType renteeType, String keyword, int pageSize, long offset);
}
