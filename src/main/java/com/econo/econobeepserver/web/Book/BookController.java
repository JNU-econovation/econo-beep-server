package com.econo.econobeepserver.web.Book;

import com.econo.econobeepserver.domain.Book.BookType;
import com.econo.econobeepserver.dto.Book.BookElementDto;
import com.econo.econobeepserver.dto.Book.BookInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @GetMapping("/book/{id}")
    public ResponseEntity<BookInfoDto> getBookInfoDtoByBookId(@PathVariable(value = "id") Long bookId) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/book/list/all")
    public ResponseEntity<List<BookElementDto>> getBookElementDtosByPaging(@RequestParam(value = "lastBookId", required = false) Long lastBookId,
                                                                                            @RequestParam(value = "pageSize") int pageSize) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/book/list/{type}")
    public ResponseEntity<List<BookElementDto>> getBookElementDtosByTypeByPaging(@PathVariable(value = "type")BookType bookType,
                                                                                                  @RequestParam(value = "lastBookId", required = false) Long lastBookId,
                                                                                                  @RequestParam(value = "pageSize") int pageSize) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/book/search")
    public ResponseEntity<List<BookElementDto>> searchBookElementDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/book/search/recommendation")
    public ResponseEntity<List<String>> getBookRecommendationsByKeyword(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok(null);
    }
}
