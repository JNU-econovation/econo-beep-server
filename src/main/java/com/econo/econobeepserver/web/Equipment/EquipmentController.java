package com.econo.econobeepserver.web.Equipment;

import com.econo.econobeepserver.domain.Equipment.EquipmentType;
import com.econo.econobeepserver.dto.Equipment.EquipmentElementDto;
import com.econo.econobeepserver.dto.Equipment.EquipmentInfoDto;
import com.econo.econobeepserver.service.Equipment.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping("/equipment/{id}")
    public ResponseEntity<EquipmentInfoDto> getEquipmentInfoDtoByEquipmentId(@PathVariable(value = "id") Long equipmentId) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/equipment/list/all")
    public ResponseEntity<List<EquipmentElementDto>> getEquipmentElementDtosByCreatedDateDescByPaging(@RequestParam(value = "lastEquipmentId", required = false) Long lastEquipmentId,
                                                                                                      @RequestParam(value = "pageSize") int pageSize) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/equipment/list/{type}")
    public ResponseEntity<List<EquipmentElementDto>> getEquipmentElementDtosByTypeByPaging(@PathVariable(value = "type") EquipmentType equipmentType,
                                                                                           @RequestParam(value = "lastEquipmentId", required = false) Long lastEquipmentId,
                                                                                           @RequestParam(value = "pageSize") int pageSize) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/equipment/search")
    public ResponseEntity<List<EquipmentElementDto>> searchEquipmentElementDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/equipment/search/recommendation")
    public ResponseEntity<List<String>> getEquipmentRecommendationsByKeyword(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok(null);
    }
}
