package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.dto.Rentee.DeviceSaveDto;
import com.econo.econobeepserver.dto.Rentee.RenteeManagementInfoDto;
import com.econo.econobeepserver.dto.Rentee.BookSaveDto;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import com.econo.econobeepserver.service.Rentee.RenteeSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RenteeManagementController {

    private final RenteeService renteeService;

    @PostMapping("/api/management/book")
    public ResponseEntity<Void> createBook(@Valid @ModelAttribute BookSaveDto bookSaveDto) {
        renteeService.createRentee(new RenteeSaveDto(bookSaveDto));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/management/device")
    public ResponseEntity<Void> createDevice(@Valid @ModelAttribute DeviceSaveDto deviceSaveDto) {
        renteeService.createRentee(new RenteeSaveDto(deviceSaveDto));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/management/search/book")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchRenteeManagementInfoDtosFromBook(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                @RequestParam(value = "sort", required = false, defaultValue = "NONE") RenteeSort renteeSort,
                                                                                                @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosByNameFromBookWithSortAndPaging(name, renteeSort, pageIndex, pageSize);

        return ResponseEntity.ok(renteeManagementInfoDtos);
    }

    @GetMapping("/api/management/search/device")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchRenteeManagementInfoDtosFromDevice(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                  @RequestParam(value = "sort", required = false, defaultValue = "NONE") RenteeSort renteeSort,
                                                                                                  @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                  @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosByNameFromDeviceWithSortAndPaging(name, renteeSort, pageIndex, pageSize);

        return ResponseEntity.ok(renteeManagementInfoDtos);
    }

    @PutMapping("/api/management/book/{id}")
    public ResponseEntity<Void> updateBookById(@PathVariable(value = "id") Long id,
                                               @Valid @ModelAttribute BookSaveDto bookSaveDto
    ) {
        renteeService.updateRenteeById(id, new RenteeSaveDto(bookSaveDto));

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/management/device/{id}")
    public ResponseEntity<Void> updateDeviceById(@PathVariable(value = "id") Long id,
                                                 @Valid @ModelAttribute DeviceSaveDto deviceSaveDto
    ) {
        renteeService.updateRenteeById(id, new RenteeSaveDto(deviceSaveDto));

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/api/management/book/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable(value = "id") Long id) {
        renteeService.deleteRenteeById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/management/device/{id}")
    public ResponseEntity<Void> deleteDeviceById(@PathVariable(value = "id") Long id) {
        renteeService.deleteRenteeById(id);

        return ResponseEntity.ok().build();
    }
}
