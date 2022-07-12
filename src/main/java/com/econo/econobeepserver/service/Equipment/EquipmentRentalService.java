package com.econo.econobeepserver.service.Equipment;

import com.econo.econobeepserver.domain.Equipment.EquipmentRepository;
import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRentalRepository;
import com.econo.econobeepserver.exception.AlreadyRentedException;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EquipmentRentalService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentRentalRepository equipmentRentalRepository;


    @Transactional
    public void rentEquipmentById(Long id)  throws NotFoundRenteeException, AlreadyRentedException, UnrentableException {
    }

    @Transactional
    public void returnEquipmentById(Long id) throws NotFoundRenteeException, NotRenterException {

    }
}
