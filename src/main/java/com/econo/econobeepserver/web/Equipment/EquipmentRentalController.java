package com.econo.econobeepserver.web.Equipment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentRentalController {

    @PutMapping("/equipment/{id}/rent")
    public ResponseEntity<Void> rentEquipmentByEquipmentId(@PathVariable(value = "id") Long equipmentId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/equipment/{id}/return")
    public ResponseEntity<Void> returnEquipmentByEquipmentId(@PathVariable(value = "id") Long equipmentId) {
        return ResponseEntity.ok(null);
    }
}
