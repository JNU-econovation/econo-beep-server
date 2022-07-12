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
        Optional<EquipmentInfoDto> equipmentInfoDto = equipmentService.getEquipmentInfoDtoById(id);

        if (equipmentInfoDto.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok(equipmentInfoDto.get());
        }
    }

    @GetMapping("/equipment/list/all")
    public ResponseEntity<List<EquipmentElementDto>> getEquipmentElementDtosByCreatedDateDescWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                                        @RequestParam(value = "lastEquipmentId", required = false, defaultValue = "0") Long lastId) {
        List<EquipmentElementDto> equipmentElementDtos = equipmentService.getEquipmentElementDtosByCreatedDateDescWithPaging(pageSize, lastId);
        return ResponseEntity.ok(equipmentElementDtos);
    }

    @GetMapping("/equipment/search")
    public ResponseEntity<List<EquipmentElementDto>> searchEquipmentElementDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<EquipmentElementDto> equipmentElementDtos = equipmentService.searchEquipmentElementDtosByKeyword(keyword);
        return ResponseEntity.ok(equipmentElementDtos);
    }

    @GetMapping("/equipment/search/recommendation")
    public ResponseEntity<List<String>> getEquipmentSearchSuggestionsByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<String> suggestions = equipmentService.getEquipmentSearchSuggestionsByKeyword(keyword);
        return ResponseEntity.ok(suggestions);
    }
}
