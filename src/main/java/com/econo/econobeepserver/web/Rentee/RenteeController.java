package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.dto.Rentee.RenteeInfoDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.econo.econobeepserver.config.BearerAuthInterceptor.USER_ID;

@Tag(name = "대여품 기본 정보 API", description = "대여품 조회, 검색, 즐겨찾기")
@RestController
@RequiredArgsConstructor
public class RenteeController {

    private final RenteeService renteeService;


    @Operation(
            summary = "대여품 정보 조회",
            description = "accessToken을 첨부하면, 해당 유저의 즐겨찾기 여부를 확인한다. (첨부하지 않으면, false)"
    )
    @GetMapping("/api/rentees/{id}")
    public ResponseEntity<RenteeInfoDto> getRenteeInfoDtoById(HttpServletRequest request,
                                                              @PathVariable(value = "id") Long id) {
        Long userId = (Long) request.getAttribute(USER_ID);
        RenteeInfoDto renteeInfoDto = renteeService.getRenteeInfoDtoByIdWithUserId(id, userId);

        return ResponseEntity.ok(renteeInfoDto);
    }


    @Operation(summary = "즐겨찾기 추가 [Token required]")
    @PutMapping("/api/rentees/{id}/bookmark")
    public ResponseEntity<Long> registerBookmark(HttpServletRequest request,
                                                 @PathVariable(value = "id") Long id) {
        long userId = (Long) request.getAttribute(USER_ID);
        long bookmarkId = renteeService.registerBookmark(id, userId).getId();

        return ResponseEntity.ok(bookmarkId);
    }


    @Operation(summary = "즐겨찾기 제거 [Token required]")
    @DeleteMapping("/api/rentees/{id}/bookmark")
    public ResponseEntity<Void> unregisterBookmark(HttpServletRequest request,
                                                   @PathVariable(value = "id") Long id) {
        long userId = (Long) request.getAttribute(USER_ID);
        renteeService.unregisterBookmark(id, userId);

        return ResponseEntity.ok().build();
    }


    @Operation(
            summary = "대여품 검색",
            description = "pageIndex: 페이지 번호 (0부터 시작)\n pageSize: 페이지 크기"
    )
    @GetMapping("/api/rentees")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByNameWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                @RequestParam(value = "pageIndex") int pageIndex,
                                                                                @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }


    @Operation(
            summary = "책 검색",
            description = "pageIndex: 페이지 번호 (0부터 시작)\n pageSize: 페이지 크기"
    )
    @GetMapping("/api/rentee/books")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByNameFromBookWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                  @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                  @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameFromBookWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }


    @Operation(
            summary = "기자재 검색",
            description = "pageIndex: 페이지 번호 (0부터 시작)\n pageSize: 페이지 크기"
    )
    @GetMapping("/api/rentee/devices")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByNameFromDeviceWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                                    @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                    @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.searchRenteeElementDtosByNameFromDeviceWithPaging(name, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }
}
