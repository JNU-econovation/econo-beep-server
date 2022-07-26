package com.econo.econobeepserver.web.Rentee;

import com.econo.econobeepserver.dto.Book.BookElementDto;
import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RenteeController {

    private final RenteeService renteeService;

    @GetMapping("/rentee/search")
    public ResponseEntity<List<RenteeElementDto>> searchRenteeElementDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<BookElementDto> bookElementDtos = renteeService.searchBookElementDtosByKeyword(keyword);

        return ResponseEntity.ok(bookElementDtos);
    }
}
