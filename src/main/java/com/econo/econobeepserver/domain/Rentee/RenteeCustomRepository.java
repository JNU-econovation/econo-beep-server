package com.econo.econobeepserver.domain.Rentee;

import java.util.List;

public interface RenteeCustomRepository {

    List<Rentee> getRenteesWithPaging(int pageSize, Long lastId);

    List<Rentee> getRenteesByTypeEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId);

    List<Rentee> getRenteesByTypeEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId, Boolean isIdAsc, Boolean isIdDesc);

    List<Rentee> getRenteesByTypeNotEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId);

    List<Rentee> getRenteesByTypeNotEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId, Boolean isIdAsc, Boolean isIdDesc);

    List<Rentee> searchRentee(String keyword);

    List<Rentee> searchRenteeByRenteeTypeEqual(RenteeType renteeType, String keyword);

    List<Rentee> searchRenteeByRenteeTypeNotEqual(RenteeType renteeType, String keyword);
}
