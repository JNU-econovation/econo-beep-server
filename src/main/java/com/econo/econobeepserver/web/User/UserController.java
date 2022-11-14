package com.econo.econobeepserver.web.User;

import com.econo.econobeepserver.domain.User.Role;
import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.dto.User.UserProfileDto;
import com.econo.econobeepserver.service.Rentee.RentalService;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import com.econo.econobeepserver.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RenteeService renteeService;
    private final RentalService rentalService;

    @GetMapping("/api/user/profile")
    public ResponseEntity<UserProfileDto> getUserInfoDtoByAccessToken(@RequestParam(value = "accessToken") String accessToken) {
        UserProfileDto userProfileDto = userService.getUserInfoDtoByAccessToken(accessToken);

        return ResponseEntity.ok(userProfileDto);
    }

    @GetMapping("/api/user/role")
    public ResponseEntity<Role> getUserRoleByAccessToken(@RequestParam(value = "accessToken") String accessToken) {
        Role userRole = userService.getUserRoleByAccessToken(accessToken);

        return ResponseEntity.ok(userRole);
    }

    @GetMapping("/api/user/{userId}/rents")
    public ResponseEntity<List<RenteeElementDto>> getRentsByUserId(@PathVariable(value = "userId") long userId) {
        List<RenteeElementDto> renteeElementDtos = rentalService.getRentedRenteesByUserId(userId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/api/user/{userId}/returns")
    public ResponseEntity<List<RenteeElementDto>> getReturnsByUserId(@PathVariable(value = "userId") long userId) {
        List<RenteeElementDto> renteeElementDtos = rentalService.getReturnedRenteeByUserId(userId);

        return ResponseEntity.ok(renteeElementDtos);
    }

    @GetMapping("/api/user/{userId}/bookmarks")
    public ResponseEntity<List<RenteeElementDto>> getBookmarksByUserId(@PathVariable(value = "userId") long userId) {
        List<RenteeElementDto> renteeElementDtos = renteeService.getBookmarkedRenteeByUserId(userId);

        return ResponseEntity.ok(renteeElementDtos);
    }
}
