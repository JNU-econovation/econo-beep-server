package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.service.Rentee.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "대여/반납 API", description = "대여, 반납")
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;


    @Operation(summary = "책 대여")
    @PutMapping("/api/rentees/{id}/rent")
    public ResponseEntity<String> rentRenteeById(@PathVariable(value = "id") Long id,
                                                 @RequestParam(value = "accessToken") String accessToken) {
        rentalService.rentRenteeByRenteeId(id, accessToken);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "책 반납")
    @PutMapping("/api/rentees/{id}/return")
    public ResponseEntity<String> returnRenteeById(@PathVariable(value = "id") Long id,
                                                   @RequestParam(value = "accessToken") String accessToken) {
        rentalService.returnRenteeByRenteeId(id, accessToken);

        return ResponseEntity.ok().build();
    }

}
