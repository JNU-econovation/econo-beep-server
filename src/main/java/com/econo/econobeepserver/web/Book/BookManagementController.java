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
    public ResponseEntity<List<BookManagementInfoDto>> getBookManagementInfoDtosByIdDescWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                                                      @RequestParam(value = "lastBookId", required = false) Long lastId) {
        List<BookManagementInfoDto> bookManagementInfoDtos = bookService.getBookManagementInfoDtosByIdDescWithPaging(pageSize, lastId);

        return ResponseEntity.ok(bookManagementInfoDtos);
    }

    @GetMapping("/management/book/search")
    public ResponseEntity<List<BookManagementInfoDto>> searchBookManagementInfoDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<BookManagementInfoDto> bookManagementInfoDtos = bookService.searchBookManagementInfoDtosByKeyword(keyword);

        return ResponseEntity.ok(bookManagementInfoDtos);
    }

    @PutMapping("/management/book/{id}")
    public ResponseEntity<Void> updateBookById(@PathVariable(value = "id") Long id,
                                                   @RequestBody BookSaveDto bookSaveDto) {
        bookService.updateBookById(id, bookSaveDto);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/management/book/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable(value = "id") Long id) {
        bookService.deleteBookById(id);

        return ResponseEntity.ok().build();
    }
}
