package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.domain.Rentee.RenteeType;
import com.econo.econobeepserver.dto.Rentee.EquipmentSaveDto;
import com.econo.econobeepserver.dto.Rentee.RenteeManagementInfoDto;
import com.econo.econobeepserver.dto.Rentee.BookSaveDto;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RenteeManagementController {

    private final RenteeService renteeService;

    @PostMapping("/management/book")
    public ResponseEntity<Void> createBook(@Valid @ModelAttribute BookSaveDto bookSaveDto) {
        renteeService.createRentee(new RenteeSaveDto(bookSaveDto));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/management/equipment")
    public ResponseEntity<Void> createEquipment(@Valid @ModelAttribute EquipmentSaveDto equipmentSaveDto) {
        renteeService.createRentee(new RenteeSaveDto(equipmentSaveDto));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/management/search/book")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchRenteeManagementInfoDtosFromBook(@RequestParam(value = "keyword") String keyword,
                                                                                                @RequestParam(value = "pageSize") int pageSize,
                                                                                                @RequestParam(value = "lastRenteeId", required = false) Long lastId,
                                                                                                @RequestParam(value = "isIdAsc", required = false) Boolean isIdAsc,
                                                                                                @RequestParam(value = "isIdDesc", required = false) Boolean isIdDesc,
                                                                                                @RequestParam(value = "isRecentRentDesc", required = false) Boolean isRecentRentDesc
    ) {
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosFromBookWithPaging(keyword, pageSize, lastId, isIdAsc, isIdDesc, isRecentRentDesc);

        return ResponseEntity.ok(renteeManagementInfoDtos);
    }

    @GetMapping("/management/search/equipment")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchRenteeManagementInfoDtosFromEquipment(@RequestParam(value = "keyword") String keyword,
                                                                                                     @RequestParam(value = "pageSize") int pageSize,
                                                                                                     @RequestParam(value = "lastRenteeId", required = false) Long lastId,
                                                                                                     @RequestParam(value = "isIdAsc", required = false) Boolean isIdAsc,
                                                                                                     @RequestParam(value = "isIdDesc", required = false) Boolean isIdDesc,
                                                                                                     @RequestParam(value = "isRecentRentDesc", required = false) Boolean isRecentRentDesc
    ) {
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosFromEquipmentWithPaging(keyword, pageSize, lastId, isIdAsc, isIdDesc, isRecentRentDesc);

        return ResponseEntity.ok(renteeManagementInfoDtos);
    }

    @PutMapping("/management/book/{id}")
    public ResponseEntity<Void> updateBookById(@PathVariable(value = "id") Long id,
                                               @Valid @ModelAttribute BookSaveDto bookSaveDto) {
        renteeService.updateRenteeById(id, new RenteeSaveDto(bookSaveDto));

        return ResponseEntity.ok().build();
    }

    @PutMapping("/management/equipment/{id}")
    public ResponseEntity<Void> updateEquipmentById(@PathVariable(value = "id") Long id,
                                                    @Valid @ModelAttribute EquipmentSaveDto equipmentSaveDto) {
        renteeService.updateRenteeById(id, new RenteeSaveDto(equipmentSaveDto));

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/management/book/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable(value = "id") Long id) {
        renteeService.deleteRenteeById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/management/equipment/{id}")
    public ResponseEntity<Void> deleteEquipmentById(@PathVariable(value = "id") Long id) {
        renteeService.deleteRenteeById(id);

        return ResponseEntity.ok().build();
    }
}
