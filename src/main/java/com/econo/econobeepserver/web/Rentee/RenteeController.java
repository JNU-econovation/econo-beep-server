package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.domain.Rentee.RenteeType;
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
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByKeyword(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                                                   @RequestParam(value = "pageSize") int pageSize,
                                                                                   @RequestParam(value = "lastRenteeId", required = false) Long lastId
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByKeyword(keyword, pageSize, lastId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search/book")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosFromBookByKeywordWithPaging(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                                                                     @RequestParam(value = "pageSize") int pageSize,
                                                                                                     @RequestParam(value = "lastRenteeId", required = false) Long lastId
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByRenteeTypeNotEqualWithPaging(RenteeType.EQUIPMENT, keyword, pageSize, lastId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search/equipment")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosFromEquipmentByKeywordWithPaging(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                                                                          @RequestParam(value = "pageSize") int pageSize,
                                                                                                          @RequestParam(value = "lastRenteeId", required = false) Long lastId
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging(RenteeType.EQUIPMENT, keyword, pageSize, lastId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search/{type}")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging(@PathVariable(value = "type") RenteeType renteeType,
                                                                                                              @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                                                                              @RequestParam(value = "pageSize") int pageSize,
                                                                                                              @RequestParam(value = "lastRenteeId", required = false) Long lastId
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging(renteeType, keyword, pageSize, lastId);

        return ResponseEntity.ok(renteeElementDtos);
    }
}
