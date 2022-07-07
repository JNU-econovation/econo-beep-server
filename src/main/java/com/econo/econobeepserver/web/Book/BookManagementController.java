package com.econo.econobeepserver.web.Book;

import com.econo.econobeepserver.dto.Book.BookManagementInfoDto;
import com.econo.econobeepserver.dto.Book.BookSaveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookManagementController {

    @PostMapping("/management/book")
    public ResponseEntity<Void> createBook(@RequestBody BookSaveDto bookSaveDto) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/management/book/list/all")
    public ResponseEntity<List<BookManagementInfoDto>> getBookManagementInfoDtosByBookIdAscByPaging(@RequestParam(value = "lastBookId", required = false) Long lastBookId,
                                                                                                    @RequestParam(value = "pageSize") int pageSize) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/management/book/search")
    public ResponseEntity<List<BookManagementInfoDto>> searchBookManagementInfoDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/management/book/{id}")
    public ResponseEntity<Void> updateBookByBookId(@PathVariable(value = "id") Long bookId,
                                                   @RequestBody BookSaveDto bookSaveDto) {
        return ResponseEntity.ok(null);
    }


    @DeleteMapping("/management/book/{id}")
    public ResponseEntity<Void> deleteBookByBookId(@PathVariable(value = "id") Long bookId) {
        return ResponseEntity.ok(null);
    }
}
