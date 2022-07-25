package com.econo.econobeepserver.service.Equipment;

import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRental;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.User.UserApi;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class EquipmentRentalService {

    private final EquipmentService equipmentService;
    private UserApi userApi;


    private void validateRentableRentee(final Equipment equipment) {
        if (equipment.getRentState() != RentState.RENTABLE) {
            throw new UnrentableException();
        }
    }

    @Transactional
    public void rentEquipmentById(Long id, String pinCode) {
        userApi.validatePinCode(pinCode);

        Equipment equipment = equipmentService.getEquipmentById(id);
        validateRentableRentee(equipment);

        UserInfoDto userInfoDto = userApi.getUserInfoDtoByPinCode(pinCode);
        EquipmentRental equipmentRental = EquipmentRental.builder()
                .renterId(userInfoDto.getUid())
                .renterName(userInfoDto.getName())
                .build();

        equipment.rentEquipment(equipmentRental);
    }


    private void validateReturnableRentee(final Equipment equipment) {
        if (equipment.getRentalHistories().isEmpty() || equipment.getRentState() != RentState.RENTED) {
            throw new UnreturnableException();
        }
    }

    private void validateRenter(final EquipmentRental equipmentRental, final Long renterId) {
        if (
                !Objects.equals(equipmentRental.getRenterId(), renterId)
        ) {
            throw new NotRenterException();
        }
    }

    @Transactional
    public void returnEquipmentById(Long id, String pinCode) {
        userApi.validatePinCode(pinCode);

        Equipment equipment = equipmentService.getEquipmentById(id);
        validateReturnableRentee(equipment);

        EquipmentRental equipmentRental = equipment.getRentalHistories().get(equipment.getRentalHistories().size() - 1);
        UserInfoDto userInfoDto = userApi.getUserInfoDtoByPinCode(pinCode);
        validateRenter(equipmentRental, userInfoDto.getUid());

        equipmentRental.returnEquipment();
        equipment.returnEquipment();
    }
}
