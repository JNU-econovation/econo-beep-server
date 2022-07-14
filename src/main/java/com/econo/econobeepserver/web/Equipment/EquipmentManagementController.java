package com.econo.econobeepserver.web.Equipment;

import com.econo.econobeepserver.dto.Equipment.EquipmentManagementInfoDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentSaveDto;
import com.econo.econobeepserver.service.Equipment.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipmentManagementController {

    private final EquipmentService equipmentService;


    @PostMapping("/management/equipment")
    public ResponseEntity<Void> createEquipment(@RequestBody EquipmentSaveDto equipmentSaveDto) {
        equipmentService.createEquipment(equipmentSaveDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/management/equipment/list/all")
    public ResponseEntity<List<EquipmentManagementInfoDto>> getEquipmentManagementInfoDtosByIdDescWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                                            @RequestParam(value = "lastEquipmentId", required = false) Long lastId) {
        List<EquipmentManagementInfoDto> equipmentManagementInfoDtos = equipmentService.getEquipmentManagementInfoDtosByIdDescWithPaging(pageSize, lastId);
        return ResponseEntity.ok(equipmentManagementInfoDtos);
    }

    @GetMapping("/management/equipment/search")
    public ResponseEntity<List<EquipmentManagementInfoDto>> searchEquipmentManagementInfoDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<EquipmentManagementInfoDto> equipmentManagementInfoDtos = equipmentService.searchEquipmentManagementInfoDtosByKeyword(keyword);
        return ResponseEntity.ok(equipmentManagementInfoDtos);
    }

    @PutMapping("/management/equipment/{id}")
    public ResponseEntity<Void> updateEquipmentById(@PathVariable(value = "id") Long id,
                                                    @RequestBody EquipmentSaveDto equipmentSaveDto) {
        equipmentService.updateEquipmentById(id, equipmentSaveDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/management/equipment/{id}")
    public ResponseEntity<Void> deleteEquipmentById(@PathVariable(value = "id") Long id) {
        equipmentService.deleteEquipmentById(id);
        return ResponseEntity.ok().build();
    }
}
