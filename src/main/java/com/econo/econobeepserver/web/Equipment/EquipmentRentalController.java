package com.econo.econobeepserver.web.Equipment;

import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EquipmentRentalController {

    private final EquipmentRentalRepository equipmentRentalRepository;

    @PutMapping("/equipment/{id}/rent")
    public ResponseEntity<Void> rentEquipmentByEquipmentId(@PathVariable(value = "id") Long equipmentId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/equipment/{id}/return")
    public ResponseEntity<Void> returnEquipmentByEquipmentId(@PathVariable(value = "id") Long equipmentId) {
        return ResponseEntity.ok(null);
    }
}
