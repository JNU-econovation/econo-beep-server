package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.dto.Rentee.RenteeInfoDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RenteeController {

    private final RenteeService renteeService;

    @GetMapping("/rentee/{id}")
    public ResponseEntity<RenteeInfoDto> getRenteeInfoDtoById(@PathVariable(value = "id") Long id) {
        RenteeInfoDto renteeInfoDto = renteeService.getRenteeInfoDtoById(id);

        return ResponseEntity.ok(renteeInfoDto);
    }

    @GetMapping("/rentee/search")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByName(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                @RequestParam(value = "pageIndex") int pageIndex,
                                                                                @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search/book")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByNameFromBookWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                     @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                     @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameFromBookWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search/equipment")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByNameFromEquipmentWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                          @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                          @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameFromEquipmentWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }
}
