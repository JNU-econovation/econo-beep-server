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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.econo.econobeepserver.config.BearerAuthInterceptor.ACCESS_TOKEN;

@Tag(name = "유저 API", description = "Econo IDP의 Token을 통한 인증, 유저 정보 조회")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RenteeService renteeService;
    private final RentalService rentalService;


    @Operation(summary = "내 프로필 조회")
    @GetMapping("/api/user/profile/my")
    public ResponseEntity<UserProfileDto> getUserInfoDtoByAccessToken(HttpServletRequest request) {
        String accessToken = request.getAttribute(ACCESS_TOKEN).toString();
        UserProfileDto userProfileDto = userService.getUserProfileDtoByAccessToken(accessToken);

        return ResponseEntity.ok(userProfileDto);
    }

    @Operation(summary = "유저 프로필 조회")
    @GetMapping("/api/user/profile/{userId}")
    public ResponseEntity<UserProfileDto> getUserInfoDtoByAccessToken(@PathVariable(value = "userId") Long userId) {
//        UserProfileDto userProfileDto = userService.getUserProfileDtoByAccessToken(accessToken);

//        return ResponseEntity.ok(userProfileDto);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "삡 서비스 내 유저 권한 조회")
    @GetMapping("/api/user/role")
    public ResponseEntity<Role> getUserRoleByAccessToken(@RequestParam(value = "accessToken") String accessToken) {
        Role userRole = userService.getUserRoleByAccessToken(accessToken);

        return ResponseEntity.ok(userRole);
    }


    @Operation(
            summary = "해당 유저가 대여한 대여품 조회",
            description = "최신에 대여한 대여품을 먼저 조회한다."
    )
    @GetMapping("/api/user/{userId}/rents")
    public ResponseEntity<List<RenteeElementDto>> getRentsByUserId(@PathVariable(value = "userId") long userId) {
        List<RenteeElementDto> renteeElementDtos = rentalService.getRentedRenteesByUserId(userId);

        return ResponseEntity.ok(renteeElementDtos);
    }


    @Operation(
            summary = "해당 유저가 반납한 대여품 조회",
            description = "최신에 반납한 대여품을 먼저 조회한다."
    )
    @GetMapping("/api/user/{userId}/returns")
    public ResponseEntity<List<RenteeElementDto>> getReturnsByUserId(@PathVariable(value = "userId") long userId) {
        List<RenteeElementDto> renteeElementDtos = rentalService.getReturnedRenteeByUserId(userId);

        return ResponseEntity.ok(renteeElementDtos);
    }


    @Operation(
            summary = "해당 유저가 즐겨찾기한 대여품 조회",
            description = "최신에 즐겨찾기한 대여품을 먼저 조회한다."
    )
    @GetMapping("/api/user/{userId}/bookmarks")
    public ResponseEntity<List<RenteeElementDto>> getBookmarksByUserId(@PathVariable(value = "userId") long userId) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getBookmarkedRenteeByUserId(userId);

        return ResponseEntity.ok(renteeElementDtos);
    }
}
