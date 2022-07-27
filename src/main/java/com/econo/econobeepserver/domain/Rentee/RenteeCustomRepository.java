package com.econo.econobeepserver.domain.Rentee;

import java.util.List;

public interface RenteeCustomRepository {

    List<Rentee> getRecentRenteeWithPaging(int pageSize, Long lastId);

    List<Rentee> getRenteeByTypeEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId);

    List<Rentee> getRenteeByTypeNotEqualWithPaging(RenteeType renteeType, int pageSize, Long lastId);

    List<Rentee> searchRenteeByKeyword(String keyword);

    List<Rentee> searchRenteeByRenteeTypeEqualByKeyword(RenteeType renteeType, String keyword);

    List<Rentee> searchRenteeByRenteeTypeNotEqualByKeyword(RenteeType renteeType, String keyword);
}
