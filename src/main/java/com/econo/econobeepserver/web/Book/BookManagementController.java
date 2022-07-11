package com.econo.econobeepserver.web.Book;

import com.econo.econobeepserver.dto.Book.BookManagementInfoDto;
import com.econo.econobeepserver.dto.Book.BookSaveDto;
import com.econo.econobeepserver.service.Book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookManagementController {

    private final BookService bookService;

    @PostMapping("/management/book")
    public ResponseEntity<Void> createBook(@RequestBody BookSaveDto bookSaveDto) {
        bookService.createBook(bookSaveDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/management/book/list/all")
    public ResponseEntity<List<BookManagementInfoDto>> getBookManagementInfoDtosByBookIdAscWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                                      @RequestParam(value = "lastBookId", required = false, defaultValue = "0") Long lastBookId) {
        List<BookManagementInfoDto> bookManagementInfoDtos = bookService.getBookManagementInfoDtosByBookIdAscWithPaging(pageSize, lastBookId);
        return ResponseEntity.ok(bookManagementInfoDtos);
    }

    @GetMapping("/management/book/search")
    public ResponseEntity<List<BookManagementInfoDto>> searchBookManagementInfoDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<BookManagementInfoDto> bookManagementInfoDtos = bookService.searchBookManagementInfoDtosByKeyword(keyword);
        return ResponseEntity.ok(bookManagementInfoDtos);
    }

    @PutMapping("/management/book/{id}")
    public ResponseEntity<Void> updateBookByBookId(@PathVariable(value = "id") Long bookId,
                                                   @RequestBody BookSaveDto bookSaveDto) {
        bookService.updateBookByBookId(bookId, bookSaveDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/management/book/{id}")
    public ResponseEntity<Void> deleteBookByBookId(@PathVariable(value = "id") Long bookId) {
        bookService.deleteBookByBookId(bookId);
        return ResponseEntity.ok().build();
    }
}
