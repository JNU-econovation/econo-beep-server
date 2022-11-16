package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.dto.Rentee.RenteeInfoDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Rentee", description = "대여품 API")
@RestController
@RequiredArgsConstructor
public class RenteeController {

    private final RenteeService renteeService;

    @GetMapping("/api/rentee/{renteeId}")
    public ResponseEntity<RenteeInfoDto> getRenteeInfoDtoById(@PathVariable(value = "renteeId") Long renteeId,
                                                              @RequestParam(value = "accessToken", required = false) String accessToken) {
        RenteeInfoDto renteeInfoDto = renteeService.getRenteeInfoDtoByIdWithAccessToken(renteeId, accessToken);

        return ResponseEntity.ok(renteeInfoDto);
    }

    @PutMapping("/api/rentee/{renteeId}/bookmark")
    public ResponseEntity<Void> registerBookmark(@PathVariable(value = "renteeId") Long renteeId,
                                                 @RequestParam(value = "accessToken") String accessToken) {
        renteeService.registerBookmark(renteeId, accessToken);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/rentee/{renteeId}/bookmark")
    public ResponseEntity<Void> unregisterBookmark(@PathVariable(value = "renteeId") Long renteeId,
                                                   @RequestParam(value = "accessToken") String accessToken) {
        renteeService.unregisterBookmark(renteeId, accessToken);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/rentee/search")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByName(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                @RequestParam(value = "pageIndex") int pageIndex,
                                                                                @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/api/rentee/search/book")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByNameFromBookWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                  @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                  @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameFromBookWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/api/rentee/search/device")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByNameFromDeviceWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                    @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                    @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameFromDeviceWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }
}
