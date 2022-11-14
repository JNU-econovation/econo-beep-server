package com.econo.econobeepserver.web.User;

import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.dto.User.UserInfoDto;
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

    @GetMapping("/api/user")
    public ResponseEntity<UserInfoDto> getUserInfoDtoByAccessToken(@RequestParam(value = "accessToken") String accessToken) {
        return null;
    }

    @GetMapping("/api/user/{userId}/rents")
    public ResponseEntity<List<RenteeElementDto>> getRentsByUserId(@PathVariable(value = "userId") long userId) {
        return null;
    }

    @GetMapping("/api/user/{userId}/returns")
    public ResponseEntity<List<RenteeElementDto>> getReturnsByUserId(@PathVariable(value = "userId") long userId) {
        return null;
    }

    @GetMapping("/api/user/{userId}/bookmarks")
    public ResponseEntity<List<RenteeElementDto>> getBookmarksByUserId(@PathVariable(value = "userId") long userId) {
        return null;
    }
}
