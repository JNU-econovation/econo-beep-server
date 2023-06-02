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

import static com.econo.econobeepserver.config.BearerAuthInterceptor.IDP_TOKEN;
import static com.econo.econobeepserver.config.BearerAuthInterceptor.USER_ID;

@Tag(name = "대여품 기본 정보 API", description = "대여품 조회, 검색, 즐겨찾기")
@RestController
@RequiredArgsConstructor
public class RenteeController {

    private final RenteeService renteeService;


    @Operation(
            summary = "대여품 정보 조회 [Token]",
            description = "accessToken을 첨부하면, 해당 유저의 즐겨찾기 여부를 확인한다. (첨부하지 않으면, false)"
    )
    @GetMapping("/api/rentees/{id}")
    public ResponseEntity<RenteeInfoDto> getRenteeInfoDtoById(HttpServletRequest request,
                                                              @PathVariable(value = "id") Long renteeId) {
        Long userId = (Long) request.getAttribute(USER_ID);
        String idpToken = (String) request.getAttribute(IDP_TOKEN);
        RenteeInfoDto renteeInfoDto = renteeService.getRenteeInfoDtoByIdWithUserId(renteeId, userId, idpToken);

        return ResponseEntity.ok(renteeInfoDto);
    }


    @Operation(summary = "즐겨찾기 추가 [Token required]")
    @PutMapping("/api/rentees/{id}/bookmark")
    public ResponseEntity<Long> registerBookmark(HttpServletRequest request,
                                                 @PathVariable(value = "id") Long renteeId) {
        Long userId = (Long) request.getAttribute(USER_ID);
        Long bookmarkId = renteeService.registerBookmark(renteeId, userId).getId();

        return ResponseEntity.ok(bookmarkId);
    }


    @Operation(summary = "즐겨찾기 제거 [Token required]")
    @DeleteMapping("/api/rentees/{id}/bookmark")
    public ResponseEntity<Void> unregisterBookmark(HttpServletRequest request,
                                                   @PathVariable(value = "id") Long renteeId) {
        Long userId = (Long) request.getAttribute(USER_ID);
        renteeService.unregisterBookmark(renteeId, userId);

        return ResponseEntity.ok().build();
    }


    @Operation(
            summary = "대여품 검색",
            description = "pageIndex: 페이지 번호 (0부터 시작)\n pageSize: 페이지 크기"
    )
    @GetMapping("/api/rentees")
    public ResponseEntity<List<RenteeElementDto>> getRenteeElementDtosByRenteeNameWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String renteeName,
                                                                                @RequestParam(value = "pageIndex") int pageIndex,
                                                                                @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByRenteeNameContainingWithPaging(renteeName, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }


    @Operation(
            summary = "책 검색",
            description = "pageIndex: 페이지 번호 (0부터 시작)\n pageSize: 페이지 크기"
    )
    @GetMapping("/api/rentee/books")
    public ResponseEntity<List<RenteeElementDto>> getRenteeElementDtosByRenteeNameFromBookWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String renteeName,
                                                                                                  @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                  @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByBookNameWithPaging(renteeName, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }


    @Operation(
            summary = "기자재 검색",
            description = "pageIndex: 페이지 번호 (0부터 시작)\n pageSize: 페이지 크기"
    )
    @GetMapping("/api/rentee/devices")
    public ResponseEntity<List<RenteeElementDto>> getRenteeElementDtosByRenteeNameFromDeviceWithPaging(@RequestParam(value = "name", required = false, defaultValue = "") String renteeName,
                                                                                                    @RequestParam(value = "pageIndex") int pageIndex,
                                                                                                    @RequestParam(value = "pageSize") int pageSize
    ) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByDeviceNameWithPaging(renteeName, pageIndex, pageSize);

        return ResponseEntity.ok(renteeElementDtos);
    }
}
