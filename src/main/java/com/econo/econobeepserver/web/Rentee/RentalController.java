package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.service.Rentee.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PutMapping("/rentee/{id}/rent")
    public ResponseEntity<String> rentRenteeById(@PathVariable(value = "id") Long id,
                                                 @RequestParam(value = "pinCode") String pinCode) {
        rentalService.rentRenteeById(id, pinCode);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/rentee/{id}/return")
    public ResponseEntity<String> returnRenteeById(@PathVariable(value = "id") Long id,
                                                   @RequestParam(value = "pinCode") String pinCode) {
        rentalService.returnRenteeById(id, pinCode);

        return ResponseEntity.ok().build();
    }

}
