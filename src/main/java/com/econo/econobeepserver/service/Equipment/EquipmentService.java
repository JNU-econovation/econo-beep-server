package com.econo.econobeepserver.service.Equipment;

import com.econo.econobeepserver.domain.Equipment.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
}
