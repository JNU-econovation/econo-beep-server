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
        return ResponseEntity.ok(null);
    }

    @GetMapping("/management/equipment/list/all")
    public ResponseEntity<List<EquipmentManagementInfoDto>> getEquipmentManagementInfoDtosByEquipmentIdAscByPaging(@RequestParam(value = "lastEquipmentId", required = false) Long lastEquipmentId,
                                                                                                    @RequestParam(value = "pageSize") int pageSize) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/management/equipment/search")
    public ResponseEntity<List<EquipmentManagementInfoDto>> searchEquipmentManagementInfoDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/management/equipment/{id}")
    public ResponseEntity<Void> updateEquipmentByEquipmentId(@PathVariable(value = "id") Long equipmentId,
                                                   @RequestBody EquipmentSaveDto equipmentSaveDto) {
        return ResponseEntity.ok(null);
    }


    @DeleteMapping("/management/equipment/{id}")
    public ResponseEntity<Void> deleteEquipmentByEquipmentId(@PathVariable(value = "id") Long equipmentId) {
        return ResponseEntity.ok(null);
    }
}
