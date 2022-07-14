package com.econo.econobeepserver.web.Equipment;

import com.econo.econobeepserver.service.Equipment.EquipmentRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EquipmentRentalController {

    private final EquipmentRentalService equipmentRentalService;


    @PutMapping("/equipment/{id}/rent")
    public ResponseEntity<String> rentEquipmentById(@PathVariable(value = "id") Long id) {
        equipmentRentalService.rentEquipmentById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/equipment/{id}/return")
    public ResponseEntity<String> returnEquipmentById(@PathVariable(value = "id") Long id) {
        equipmentRentalService.returnEquipmentById(id);

        return ResponseEntity.ok().build();
    }
}
