package com.econo.econobeepserver.service.Equipment;

import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.Equipment.EquipmentImage;
import com.econo.econobeepserver.domain.Equipment.EquipmentImageRepository;
import com.econo.econobeepserver.domain.Equipment.EquipmentRepository;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.dto.Equipment.EquipmentElementDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentManagementInfoDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentElementDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentInfoDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentManagementInfoDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentSaveDto;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentImageRepository equipmentImageRepository;

    private final ImageHandler imageHandler;


    @Transactional
    public long createEquipment(EquipmentSaveDto equipmentSaveDto) {
        Equipment equipment = equipmentSaveDto.toEntity();
        EquipmentImage equipmentImage = imageHandler.parseEquipmentImage(equipmentSaveDto.getEquipmentImage());
        equipment.setEquipmentCoverImage(equipmentImage);
        equipmentImage.setEquipment(equipment);

        imageHandler.downloadImage(equipmentSaveDto.getEquipmentImage(), equipmentImage.getFilePath());
        long equipmentId = equipmentRepository.save(equipment).getId();
        equipmentImageRepository.save(equipmentImage);

        return equipmentId;
    }


    public Equipment getEquipmentById(Long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (equipment.isEmpty()) {
            throw new NotFoundRenteeException();
        }

        return equipment.get();
    }

    public EquipmentInfoDto getEquipmentInfoDtoById(Long id) {
        Equipment equipment = getEquipmentById(id);

        return new EquipmentInfoDto(equipment);
    }

    public List<EquipmentElementDto> getEquipmentElementDtosWithPaging(int pageSize, Long lastId) {
        List<Equipment> equipments = equipmentRepository.getRecentEquipmentWithPaging(pageSize, lastId);

        return equipments.stream().map(EquipmentElementDto::new).collect(Collectors.toList());
    }

    public List<EquipmentElementDto> getEquipmentElementDtosByEquipmentTypeWithPaging(RenteeType equipmentType, int pageSize, Long lastId) {
        List<Equipment> equipments = equipmentRepository.getEquipmentByTypeWithPaging(equipmentType, pageSize, lastId);

        return equipments.stream().map(EquipmentElementDto::new).collect(Collectors.toList());
    }

    public List<EquipmentElementDto> searchEquipmentElementDtosByKeyword(String keyword) {
        List<Equipment> equipments = equipmentRepository.searchEquipmentByKeyword(keyword);

        return equipments.stream().map(EquipmentElementDto::new).collect(Collectors.toList());
    }

    public List<EquipmentManagementInfoDto> getEquipmentManagementInfoDtosByIdDescWithPaging(int pageSize, Long lastId) {
        List<Equipment> equipments = equipmentRepository.getRecentEquipmentWithPaging(pageSize, lastId);

        return equipments.stream().map(EquipmentManagementInfoDto::new).collect(Collectors.toList());
    }

    public List<EquipmentManagementInfoDto> searchEquipmentManagementInfoDtosByKeyword(String keyword) {
        List<Equipment> equipments = equipmentRepository.searchEquipmentByKeyword(keyword);

        return equipments.stream().map(EquipmentManagementInfoDto::new).collect(Collectors.toList());
    }

    public String getEquipmentCoverImageFilePathByEquipmentId(long equipmentId) {
        Equipment equipment = getEquipmentById(equipmentId);
        return equipment.getEquipmentImage().getFilePath();
    }


    @Transactional
    public void updateEquipmentById(Long id, EquipmentSaveDto equipmentSaveDto) {
        Equipment equipment = getEquipmentById(id);
        EquipmentImage oldEquipmentImage = equipment.getEquipmentImage();
        EquipmentImage newEquipmentImage = imageHandler.parseEquipmentImage(equipmentSaveDto.getEquipmentImage());

        imageHandler.deleteImage(oldEquipmentImage.getFilePath());
        equipmentImageRepository.deleteById(oldEquipmentImage.getId());

        equipment.updateEquipment(equipmentSaveDto);
        equipment.setEquipmentCoverImage(newEquipmentImage);
        newEquipmentImage.setEquipment(equipment);

        imageHandler.downloadImage(equipmentSaveDto.getEquipmentImage(), newEquipmentImage.getFilePath());
        equipmentImageRepository.save(newEquipmentImage);
    }


    @Transactional
    public void deleteEquipmentById(Long id) {
        try {
            Equipment equipment = getEquipmentById(id);
            equipment.clearAttributes();

            equipmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundRenteeException();
        }

    }
}
