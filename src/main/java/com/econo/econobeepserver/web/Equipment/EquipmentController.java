package com.econo.econobeepserver.web.Equipment;

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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping("/equipment/{id}")
    public ResponseEntity<EquipmentInfoDto> getEquipmentInfoDtoById(@PathVariable(value = "id") Long id) {
        EquipmentInfoDto equipmentInfoDto = equipmentService.getEquipmentInfoDtoById(id);

        return ResponseEntity.ok(equipmentInfoDto);
    }

    @GetMapping("/equipment/list/all")
    public ResponseEntity<List<EquipmentElementDto>> getEquipmentElementDtosWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                       @RequestParam(value = "lastEquipmentId", required = false) Long lastId) {
        List<EquipmentElementDto> equipmentElementDtos = equipmentService.getEquipmentElementDtosWithPaging(pageSize, lastId);

        return ResponseEntity.ok(equipmentElementDtos);
    }

    @GetMapping("/equipment/search")
    public ResponseEntity<List<EquipmentElementDto>> searchEquipmentElementDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<EquipmentElementDto> equipmentElementDtos = equipmentService.searchEquipmentElementDtosByKeyword(keyword);

        return ResponseEntity.ok(equipmentElementDtos);
    }
}
