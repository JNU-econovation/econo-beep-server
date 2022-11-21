package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.service.Rentee.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.econo.econobeepserver.config.BearerAuthInterceptor.USER_ID;

@Slf4j
@Tag(name = "대여/반납 API", description = "대여, 반납")
@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;


    @Operation(summary = "책 대여 [Token required]")
    @PutMapping("/api/rentees/{id}/rent")
    public ResponseEntity<String> rentRenteeById(HttpServletRequest request,
                                                 @PathVariable(value = "id") Long renteeId) {
        Long userId = (Long) request.getAttribute(USER_ID);
        rentalService.rentRenteeByRenteeIdAndUserId(renteeId, userId);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "책 반납 [Token required]")
    @PutMapping("/api/rentees/{id}/return")
    public ResponseEntity<String> returnRenteeById(HttpServletRequest request,
                                                   @PathVariable(value = "id") Long renteeId) {
        Long userId = (Long) request.getAttribute(USER_ID);
        rentalService.returnRenteeByRenteeId(renteeId, userId);

        return ResponseEntity.ok().build();
    }

}
