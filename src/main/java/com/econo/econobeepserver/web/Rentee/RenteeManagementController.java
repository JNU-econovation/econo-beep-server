package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.domain.User.Role;
import com.econo.econobeepserver.dto.Rentee.*;
import com.econo.econobeepserver.exception.ForbiddenRoleException;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import com.econo.econobeepserver.service.Rentee.RenteeSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.econo.econobeepserver.config.BearerAuthInterceptor.USER_ROLE;

@Tag(name = "대여품 관리 API", description = "대여품 추가, 수정, 삭제, 정렬 [Only Admin]")
@RestController
@RequiredArgsConstructor
public class RenteeManagementController {

    private final RenteeService renteeService;


    private void validateUserRole(HttpServletRequest request) {
        Role role = (Role) request.getAttribute(USER_ROLE);
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
        long bookId = renteeService.create(new RenteeSaveDto(bookSaveDto)).getId();

        return ResponseEntity.ok(bookId);
    }


    @Operation(
            summary = "기자재 추가 [Token required]",
            description = "성공하면 생성된 기자재의 id를 반환한다."
    )
    @PostMapping("/api/management/devices")
    public ResponseEntity<Long> createDevice(HttpServletRequest request, @Valid @ModelAttribute DeviceSaveDto deviceSaveDto) {
        validateUserRole(request);
        long deviceId = renteeService.create(new RenteeSaveDto(deviceSaveDto)).getId();

        return ResponseEntity.ok(deviceId);
    }


    @Operation(
            summary = "책 검색 및 정렬 [Token required]",
            description = "검색과 정렬 파라미터를 비우면, 필터가 적용되지 않는 상태로 조회한다."
    )
    @GetMapping("/api/management/books")
    public ResponseEntity<BookManagementDto> searchRenteesFromBook(HttpServletRequest request,
                                                                                    @RequestParam(value = "renteeName", required = false, defaultValue = "") String renteeName,
                                                                                    @RequestParam(value = "sort", required = false, defaultValue = "NONE") RenteeSort renteeSort,
                                                                                    @RequestParam(value = "pageIndex") int pageIndex,
                                                                                    @RequestParam(value = "pageSize") int pageSize
    ) {
        validateUserRole(request);
        Long count = renteeService.countByBookNameContainingWithSort(renteeName, renteeSort);
        List<BookManagementElementDto> bookManagementElementDtos = renteeService.getRenteeManagementInfoDtosByBookNameContainingWithSortAndPaging(renteeName, renteeSort, pageIndex, pageSize)
                .stream().map(BookManagementElementDto::new).collect(Collectors.toList());

        BookManagementDto bookManagementDto = new BookManagementDto(count, bookManagementElementDtos);

        return ResponseEntity.ok(bookManagementDto);
    }


    @Operation(
            summary = "기자재 검색 및 정렬 [Token required]",
            description = "검색과 정렬 파라미터를 비우면, 필터가 적용되지 않는 상태로 조회한다."
    )
    @GetMapping("/api/management/devices")
    public ResponseEntity<DeviceManagementDto> searchRenteesFromDevice(HttpServletRequest request,
                                                                                                   @RequestParam(value = "renteeName", required = false, defaultValue = "") String renteeName,
                                                                                                   @RequestParam(value = "sort", required = false, defaultValue = "NONE") RenteeSort renteeSort,
                                                                                                   @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                   @RequestParam(value = "pageSize") int pageSize
    ) {
        validateUserRole(request);
        Long count = renteeService.countByDeviceNameContainingWithSort(renteeName, renteeSort);
        List<DeviceManagementElementDto> deviceManagementElementDtos = renteeService.getRenteeManagementInfoDtosByDeviceNameContainingWithSortAndPaging(renteeName, renteeSort, pageIndex, pageSize)
                .stream().map(DeviceManagementElementDto::new).collect(Collectors.toList());

        DeviceManagementDto deviceManagementDto = new DeviceManagementDto(count, deviceManagementElementDtos);

        return ResponseEntity.ok(deviceManagementDto);
    }


    @Operation(summary = "책 수정 [Token required]")
    @PutMapping("/api/management/books/{id}")
    public ResponseEntity<Void> updateBookByBookId(HttpServletRequest request,
                                                   @PathVariable(value = "id") Long bookId,
                                                   @Valid @ModelAttribute BookSaveDto bookSaveDto
    ) {
        validateUserRole(request);
        renteeService.updateByRenteeId(bookId, new RenteeSaveDto(bookSaveDto));

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "기자재 수정 [Token required]")
    @PutMapping("/api/management/devices/{id}")
    public ResponseEntity<Void> updateDeviceByDeviceId(HttpServletRequest request,
                                                       @PathVariable(value = "id") Long DeviceId,
                                                       @Valid @ModelAttribute DeviceSaveDto deviceSaveDto
    ) {
        validateUserRole(request);
        renteeService.updateByRenteeId(DeviceId, new RenteeSaveDto(deviceSaveDto));

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "책 삭제 [Token required]")
    @DeleteMapping("/api/management/books/{id}")
    public ResponseEntity<Void> deleteBookByBookId(HttpServletRequest request,
                                                   @PathVariable(value = "id") Long bookId) {
        validateUserRole(request);
        renteeService.deleteByRenteeId(bookId);

        return ResponseEntity.ok().build();
    }


    @Operation(summary = "기자재 삭제 [Token required]")
    @DeleteMapping("/api/management/devices/{id}")
    public ResponseEntity<Void> deleteDeviceByDeviceId(HttpServletRequest request,
                                                       @PathVariable(value = "id") Long deviceId) {
        validateUserRole(request);
        renteeService.deleteByRenteeId(deviceId);

        return ResponseEntity.ok().build();
    }
}
