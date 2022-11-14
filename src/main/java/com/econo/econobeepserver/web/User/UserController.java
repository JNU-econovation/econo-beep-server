package com.econo.econobeepserver.web.User;

import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<UserInfoDto> getUserInfoDtoByAccessToken(@RequestParam(value = "accessToken") String accessToken) {
        return null;
    }

    @GetMapping("/api/user/rents")
    public ResponseEntity<List<RenteeElementDto>> getRentsByAccessToken(@RequestParam(value = "accessToken") String accessToken) {
        return null;
    }

    @GetMapping("/api/user/returns")
    public ResponseEntity<List<RenteeElementDto>> getReturnsByAccessToken(@RequestParam(value = "accessToken") String accessToken) {
        return null;
    }

    @GetMapping("/api/user/bookmarks")
    public ResponseEntity<List<RenteeElementDto>> getBookmarksByAccessToken(@RequestParam(value = "accessToken") String accessToken) {
        return null;
    }
}
