package com.econo.econobeepserver.web.User;

import com.econo.econobeepserver.domain.User.Role;
import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.dto.User.UserProfileDto;
import com.econo.econobeepserver.service.Rentee.RentalService;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import com.econo.econobeepserver.service.User.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.econo.econobeepserver.config.BearerAuthInterceptor.USER_ROLE;
import static com.econo.econobeepserver.config.BearerAuthInterceptor.USER_ID;


@Tag(name = "유저 API", description = "Econo IDP의 Token을 통한 인증, 유저 정보 조회")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RenteeService renteeService;
    private final RentalService rentalService;


    @Operation(summary = "내 프로필 조회 [Token required]")
    @GetMapping("/api/user/my/profile")
    public ResponseEntity<UserProfileDto> getUserInfoDtoByAccessToken(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(USER_ID);
        UserProfileDto userProfileDto = userService.getUserProfileDtoByUserId(userId);

        return ResponseEntity.ok(userProfileDto);
    }

    @Operation(summary = "유저 프로필 조회")
    @GetMapping("/api/user/{id}/profile")
    public ResponseEntity<UserProfileDto> getUserInfoDtoByAccessToken(@PathVariable(value = "id") Long id) {
        UserProfileDto userProfileDto = userService.getUserProfileDtoByUserId(id);

        return ResponseEntity.ok(userProfileDto);
    }


    @Operation(summary = "유저 권한 조회 [Token required]")
    @GetMapping("/api/user/my/role")
    public ResponseEntity<Role> getUserRoleByAccessToken(HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute(USER_ROLE);

        return ResponseEntity.ok(userRole);
    }


    @Operation(
            summary = "유저가 대여하고 있는 대여품들을 조회",
            description = "최신에 대여한 대여품 먼저 조회한다."
    )
    @GetMapping("/api/user/{id}/rents")
    public ResponseEntity<List<RenteeElementDto>> getRentsByUserId(@PathVariable(value = "id") long id) {
        List<RenteeElementDto> renteeElementDtos = rentalService.getRentingRenteesByUserId(id);

        return ResponseEntity.ok(renteeElementDtos);
    }


    @Operation(
            summary = "유저가 반납한 대여품들을 조회",
            description = "최신에 반납한 대여품 먼저 조회한다."
    )
    @GetMapping("/api/user/{id}/returns")
    public ResponseEntity<List<RenteeElementDto>> getReturnsByUserId(@PathVariable(value = "id") long id) {
        List<RenteeElementDto> renteeElementDtos = rentalService.getReturnedRenteeByUserId(id);

        return ResponseEntity.ok(renteeElementDtos);
    }


    @Operation(
            summary = "해당 유저가 즐겨찾기한 대여품 조회",
            description = "최신에 즐겨찾기한 대여품을 먼저 조회한다."
    )
    @GetMapping("/api/user/{id}/bookmarks")
    public ResponseEntity<List<RenteeElementDto>> getBookmarksByUserId(@PathVariable(value = "id") long id) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getBookmarkedRenteeByUserId(id);

        return ResponseEntity.ok(renteeElementDtos);
    }
}
