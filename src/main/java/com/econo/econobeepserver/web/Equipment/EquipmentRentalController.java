package com.econo.econobeepserver.web.Equipment;

import com.econo.econobeepserver.service.Equipment.EquipmentRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EquipmentRentalController {

    private final EquipmentRentalService equipmentRentalService;


    @PutMapping("/equipment/{id}/rent")
    public ResponseEntity<String> rentEquipmentById(@PathVariable(value = "id") Long id,
                                                    @RequestParam(value = "pinCode") String pinCode) {
        equipmentRentalService.rentEquipmentById(id, pinCode);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/equipment/{id}/return")
    public ResponseEntity<String> returnEquipmentById(@PathVariable(value = "id") Long id,
                                                      @RequestParam(value = "pinCode") String pinCode) {
        equipmentRentalService.returnEquipmentById(id, pinCode);

        return ResponseEntity.ok().build();
    }
}
