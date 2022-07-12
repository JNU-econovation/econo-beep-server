package com.econo.econobeepserver.service.Equipment;

import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.Equipment.EquipmentRepository;
import com.econo.econobeepserver.dto.Equipment.EquipmentElementDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentInfoDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentManagementInfoDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;


    public void createEquipment(EquipmentSaveDto equipmentSaveDto) {
    }


    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    public Optional<EquipmentInfoDto> getEquipmentInfoDtoById(Long id) {
        return null;
    }

    public List<EquipmentElementDto> getEquipmentElementDtosByCreatedDateDescWithPaging(int pageSize, Long lastId) {
        return null;
    }

    public List<EquipmentElementDto> searchEquipmentElementDtosByKeyword(String keyword) {
        return null;
    }

    public List<String> getEquipmentSearchSuggestionsByKeyword(String keyword) {
        return null;
    }

    public List<EquipmentManagementInfoDto> getEquipmentManagementInfoDtosByIdAscWithPaging(int pageSize, Long lastId) {
        return null;
    }

    public List<EquipmentManagementInfoDto> searchEquipmentManagementInfoDtosByKeyword(String keyword) {
        return null;
    }


    @Transactional
    public void updateEquipmentById(Long id, EquipmentSaveDto equipmentSaveDto) {
    }


    @Transactional
    public void deleteEquipmentById(Long id) {
    }
}
