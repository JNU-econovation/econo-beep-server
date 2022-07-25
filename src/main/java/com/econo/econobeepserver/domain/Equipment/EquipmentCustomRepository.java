package com.econo.econobeepserver.domain.Equipment;

import com.econo.econobeepserver.domain.RenteeType;

import java.util.List;

public interface EquipmentCustomRepository {

    List<Equipment> getRecentEquipmentWithPaging(int pageSize, Long lastId);

    List<Equipment> getEquipmentByTypeWithPaging(RenteeType renteeType, int pageSize, Long lastId);

    List<Equipment> searchEquipmentByKeyword(String keyword);
}
