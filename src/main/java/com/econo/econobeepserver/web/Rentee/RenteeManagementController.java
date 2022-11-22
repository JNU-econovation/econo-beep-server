package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.domain.User.Role;
import com.econo.econobeepserver.dto.Rentee.DeviceSaveDto;
import com.econo.econobeepserver.dto.Rentee.RenteeManagementInfoDto;
import com.econo.econobeepserver.dto.Rentee.BookSaveDto;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.econo.econobeepserver.exception.ForbiddenRoleException;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import com.econo.econobeepserver.service.Rentee.RenteeSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.econo.econobeepserver.config.BearerAuthInterceptor.USER_ROLE;

@Tag(name = "대여품 관리 API", description = "대여품 추가, 수정, 삭제, 정렬 [Only Admin]")
@RestController
@RequiredArgsConstructor
public class RenteeManagementController {

    private final RenteeService renteeService;


    private void validateUserRole(HttpServletRequest request) {
        System.out.println(request.getAttribute(USER_ROLE));
        Role role = (Role) request.getAttribute(USER_ROLE);
        System.out.println(role);
        if (!role.equals(Role.ADMIN)) {
            throw new ForbiddenRoleException();
        }
    }

    @Operation(
            summary = "책 추가 [Token required]",
            description = "성공하면 생성된 책의 id를 반환한다."
    )
    @PostMapping("/api/management/books")
    public ResponseEntity<Long> createBook(HttpServletRequest request, @Valid @ModelAttribute BookSaveDto bookSaveDto) {
        validateUserRole(request);
        long bookId = renteeService.createRentee(new RenteeSaveDto(bookSaveDto)).getId();

        return ResponseEntity.ok(bookId);
    }


    @Operation(
            summary = "기자재 추가 [Token required]",
            description = "성공하면 생성된 기자재의 id를 반환한다."
    )
    @PostMapping("/api/management/devices")
    public ResponseEntity<Long> createDevice(HttpServletRequest request, @Valid @ModelAttribute DeviceSaveDto deviceSaveDto) {
        validateUserRole(request);
        long deviceId = renteeService.createRentee(new RenteeSaveDto(deviceSaveDto)).getId();

        return ResponseEntity.ok(deviceId);
    }


    @Operation(
            summary = "책 검색 및 정렬 [Token required]",
            description = "검색과 정렬 파라미터를 비우면, 필터가 적용되지 않는 상태로 조회한다."
    )
    @GetMapping("/api/management/books")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchRenteeManagementInfoDtosFromBook(HttpServletRequest request,
                                                                                                @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                @RequestParam(value = "sort", required = false, defaultValue = "NONE") RenteeSort renteeSort,
                                                                                                @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                @RequestParam(value = "pageSize") int pageSize
    ) {
        validateUserRole(request);
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosByNameFromBookWithSortAndPaging(name, renteeSort, pageIndex, pageSize);

        return ResponseEntity.ok(renteeManagementInfoDtos);
    }


    @Operation(
            summary = "기자재 검색 및 정렬 [Token required]",
            description = "검색과 정렬 파라미터를 비우면, 필터가 적용되지 않는 상태로 조회한다."
    )
    @GetMapping("/api/management/devices")
    public ResponseEntity<List<RenteeManagementInfoDto>> searchRenteeManagementInfoDtosFromDevice(HttpServletRequest request,
                                                                                                  @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                  @RequestParam(value = "sort", required = false, defaultValue = "NONE") RenteeSort renteeSort,
                                                                                                  @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                  @RequestParam(value = "pageSize") int pageSize
    ) {
        validateUserRole(request);
        List<RenteeManagementInfoDto> renteeManagementInfoDtos = renteeService.searchRenteeManagementInfoDtosByNameFromDeviceWithSortAndPaging(name, renteeSort, pageIndex, pageSize);

        return ResponseEntity.ok(renteeManagementInfoDtos);
    }


    @Operation(summary = "책 수정 [Token required]")
    @PutMapping("/api/management/books/{id}")
    public ResponseEntity<Void> updateBookById(HttpServletRequest request,
                                               @PathVariable(value = "id") Long id,
                                               @Valid @ModelAttribute BookSaveDto bookSaveDto
    ) {
        validateUserRole(request);
        renteeService.updateRenteeById(id, new RenteeSaveDto(bookSaveDto));

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "기자재 수정 [Token required]")
    @PutMapping("/api/management/devices/{id}")
    public ResponseEntity<Void> updateDeviceById(HttpServletRequest request,
                                                 @PathVariable(value = "id") Long id,
                                                 @Valid @ModelAttribute DeviceSaveDto deviceSaveDto
    ) {
        validateUserRole(request);
        renteeService.updateRenteeById(id, new RenteeSaveDto(deviceSaveDto));

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "책 삭제 [Token required]")
    @DeleteMapping("/api/management/books/{id}")
    public ResponseEntity<Void> deleteBookById(HttpServletRequest request,
                                               @PathVariable(value = "id") Long id) {
        validateUserRole(request);
        renteeService.deleteRenteeById(id);

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "기자재 삭제 [Token required]")
    @DeleteMapping("/api/management/devices/{id}")
    public ResponseEntity<Void> deleteDeviceById(HttpServletRequest request,
                                                 @PathVariable(value = "id") Long id) {
        validateUserRole(request);
        renteeService.deleteRenteeById(id);

        return ResponseEntity.ok().build();
    }
}
