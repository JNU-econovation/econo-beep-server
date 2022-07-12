package com.econo.econobeepserver.web.Equipment;

import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRentalRepository;
import com.econo.econobeepserver.exception.AlreadyRentedException;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import com.econo.econobeepserver.service.Equipment.EquipmentRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        try {
            equipmentRentalService.rentEquipmentById(id);
            return ResponseEntity.ok().build();

        } catch (NotFoundRenteeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (AlreadyRentedException | UnrentableException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/equipment/{id}/return")
    public ResponseEntity<String> returnEquipmentById(@PathVariable(value = "id") Long id) {
        try {
            equipmentRentalService.returnEquipmentById(id);
            return ResponseEntity.ok().build();

        } catch (NotFoundRenteeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (NotRenterException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
