package com.econo.econobeepserver.service.Equipment;

import com.econo.econobeepserver.domain.Equipment.EquipmentRepository;
import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentRentalService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentRentalRepository equipmentRentalRepository;
}
