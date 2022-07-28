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

    @GetMapping("/management/list/book")
    public ResponseEntity<List<RenteeManagementInfoDto>> getBookManagementInfoDtosBySortWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                                   @RequestParam(value = "lastRenteeId", required = false) Long lastId,
                                                                                                   @RequestParam(value = "idAsc", required = false) Boolean isIdAsc,
                                                                                                   @RequestParam(value = "idDesc", required = false) Boolean isIdDesc
    ) {

        List<RenteeManagementInfoDto> bookManagementInfoDtos
                = renteeService.getRenteeManagementInfoDtosByTypeNotEqualWithPaging(RenteeType.EQUIPMENT, pageSize, lastId, isIdAsc, isIdDesc);

        return ResponseEntity.ok(bookManagementInfoDtos);
    }

    @GetMapping("/management/list/equipment")
    public ResponseEntity<List<RenteeManagementInfoDto>> getEquipmentManagementInfoDtosBySortWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                                        @RequestParam(value = "lastRenteeId", required = false) Long lastId,
                                                                                                        @RequestParam(value = "idAsc", required = false) Boolean isIdAsc,
                                                                                                        @RequestParam(value = "idDesc", required = false) Boolean isIdDesc
    ) {

        List<RenteeManagementInfoDto> equipmentManagementInfoDtos
                = renteeService.getRenteeManagementInfoDtosByTypeEqualWithPaging(RenteeType.EQUIPMENT, pageSize, lastId, isIdAsc, isIdDesc);

        return ResponseEntity.ok(equipmentManagementInfoDtos);
    }

    @GetMapping("/management/search/book")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchBookManagementInfoDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosByKeyword(keyword);

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
