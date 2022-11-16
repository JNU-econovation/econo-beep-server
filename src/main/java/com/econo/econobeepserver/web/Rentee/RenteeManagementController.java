package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.dto.Rentee.DeviceSaveDto;
import com.econo.econobeepserver.dto.Rentee.RenteeManagementInfoDto;
import com.econo.econobeepserver.dto.Rentee.BookSaveDto;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import com.econo.econobeepserver.service.Rentee.RenteeSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "대여품 관리 API", description = "대여품 추가, 수정, 삭제, 정렬")
@RestController
@RequiredArgsConstructor
@Slf4j
public class RenteeManagementController {

    private final RenteeService renteeService;


    @Operation(
            summary = "책 추가",
            description = "성공하면 생성된 책의 id를 반환한다."
    )
    @PostMapping("/api/management/book")
    public ResponseEntity<Long> createBook(@Valid @ModelAttribute BookSaveDto bookSaveDto) {
        long bookId = renteeService.createRentee(new RenteeSaveDto(bookSaveDto)).getId();

        return ResponseEntity.ok(bookId);
    }


    @Operation(
            summary = "기자재 추가",
            description = "성공하면 생성된 기자재의 id를 반환한다."
    )
    @PostMapping("/api/management/device")
    public ResponseEntity<Long> createDevice(@Valid @ModelAttribute DeviceSaveDto deviceSaveDto) {
        long deviceId = renteeService.createRentee(new RenteeSaveDto(deviceSaveDto)).getId();

        return ResponseEntity.ok(deviceId);
    }


    @Operation(
            summary = "책 검색 및 정렬",
            description = "검색과 정렬 파라미터를 비우면, 필터가 적용되지 않는 상태로 조회한다."
    )
    @GetMapping("/api/management/search/book")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchRenteeManagementInfoDtosFromBook(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                @RequestParam(value = "sort", required = false, defaultValue = "NONE") RenteeSort renteeSort,
                                                                                                @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosByNameFromBookWithSortAndPaging(name, renteeSort, pageIndex, pageSize);

        return ResponseEntity.ok(renteeManagementInfoDtos);
    }


    @Operation(
            summary = "기자재 검색 및 정렬",
            description = "검색과 정렬 파라미터를 비우면, 필터가 적용되지 않는 상태로 조회한다."
    )
    @GetMapping("/api/management/search/device")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchRenteeManagementInfoDtosFromDevice(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                  @RequestParam(value = "sort", required = false, defaultValue = "NONE") RenteeSort renteeSort,
                                                                                                  @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                  @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosByNameFromDeviceWithSortAndPaging(name, renteeSort, pageIndex, pageSize);

        return ResponseEntity.ok(renteeManagementInfoDtos);
    }


    @Operation(summary = "책 수정")
    @PutMapping("/api/management/book/{id}")
    public ResponseEntity<Void> updateBookById(@PathVariable(value = "id") Long id,
                                               @Valid @ModelAttribute BookSaveDto bookSaveDto
    ) {
        renteeService.updateRenteeById(id, new RenteeSaveDto(bookSaveDto));

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "기자재 수정")
    @PutMapping("/api/management/device/{id}")
    public ResponseEntity<Void> updateDeviceById(@PathVariable(value = "id") Long id,
                                                 @Valid @ModelAttribute DeviceSaveDto deviceSaveDto
    ) {
        renteeService.updateRenteeById(id, new RenteeSaveDto(deviceSaveDto));

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "책 삭제")
    @DeleteMapping("/api/management/book/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable(value = "id") Long id) {
        renteeService.deleteRenteeById(id);

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "기자재 삭제")
    @DeleteMapping("/api/management/device/{id}")
    public ResponseEntity<Void> deleteDeviceById(@PathVariable(value = "id") Long id) {
        renteeService.deleteRenteeById(id);

        return ResponseEntity.ok().build();
    }
}
