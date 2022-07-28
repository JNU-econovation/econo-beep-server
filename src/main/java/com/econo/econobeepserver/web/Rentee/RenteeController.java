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

    @GetMapping("/rentee/list/all")
    public ResponseEntity<List<RenteeElementDto>> getRenteeElementDtosWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                 @RequestParam(value = "lastRenteeId", required = false) Long lastId) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosWithPaging(pageSize, lastId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/list/book")
    public ResponseEntity<List<RenteeElementDto>> getRenteeElementDtosByBookWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                       @RequestParam(value = "lastRenteeId", required = false) Long lastId) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByRenteeTypeNotEqualWithPaging(RenteeType.EQUIPMENT, pageSize, lastId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/list/equipment")
    public ResponseEntity<List<RenteeElementDto>> getRenteeElementDtosByEquipmentWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                            @RequestParam(value = "lastRenteeId", required = false) Long lastId) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByRenteeTypeEqualWithPaging(RenteeType.EQUIPMENT, pageSize, lastId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/list/{type}")
    public ResponseEntity<List<RenteeElementDto>> getRenteeElementDtosByRenteeTypeEqualWithPaging(@PathVariable(value = "type") RenteeType renteeType,
                                                                                                  @RequestParam(value = "pageSize") int pageSize,
                                                                                                  @RequestParam(value = "lastRenteeId", required = false) Long lastId) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByRenteeTypeEqualWithPaging(renteeType, pageSize, lastId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByKeyword(keyword);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search/book")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByBookByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByRenteeTypeNotEqualByKeyword(RenteeType.EQUIPMENT, keyword);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search/equipment")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByEquipmentByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByRenteeTypeEqualByKeyword(RenteeType.EQUIPMENT, keyword);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/rentee/search/{type}")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByRenteeTypeEqualByKeyword(@PathVariable(value = "type") RenteeType renteeType,
                                                                                                    @RequestParam(value = "keyword") String keyword) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByRenteeTypeEqualByKeyword(renteeType, keyword);

        return ResponseEntity.ok(renteeElementDtos);
    }
}