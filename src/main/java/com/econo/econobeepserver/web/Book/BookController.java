package com.econo.econobeepserver.web.Book;

import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.dto.Book.BookElementDto;
import com.econo.econobeepserver.dto.Book.BookInfoDto;
import com.econo.econobeepserver.service.Book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<BookInfoDto> getBookInfoDtoById(@PathVariable(value = "id") Long id) {
        BookInfoDto bookInfoDto = bookService.getBookInfoDtoById(id);

        return ResponseEntity.ok(bookInfoDto);
    }

    @GetMapping("/book/list/all")
    public ResponseEntity<List<BookElementDto>> getBookElementDtosWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                             @RequestParam(value = "lastBookId", required = false) Long lastId) {
        List<BookElementDto> bookElementDtos = bookService.getBookElementDtosWithPaging(pageSize, lastId);

        return ResponseEntity.ok(bookElementDtos);
    }

    @GetMapping("/book/list/{type}")
    public ResponseEntity<List<BookElementDto>> getBookElementDtosByBookTypeWithPaging(@PathVariable(value = "type") RenteeType bookType,
                                                                                       @RequestParam(value = "pageSize") int pageSize,
                                                                                       @RequestParam(value = "lastBookId", required = false) Long lastId) {
        List<BookElementDto> bookElementDtos = bookService.getBookElementDtosByBookTypeWithPaging(bookType, pageSize, lastId);

        return ResponseEntity.ok(bookElementDtos);
    }

    @GetMapping("/book/search")
    public ResponseEntity<List<BookElementDto>> searchBookElementDtosByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<BookElementDto> bookElementDtos = bookService.searchBookElementDtosByKeyword(keyword);

        return ResponseEntity.ok(bookElementDtos);
    }

    @GetMapping("/book/search/suggestion")
    public ResponseEntity<List<String>> getBookSearchSuggestionsByKeyword(@RequestParam(value = "keyword") String keyword) {
        List<String> suggestions = bookService.getBookSearchSuggestionsByKeyword(keyword);

        return ResponseEntity.ok(suggestions);
    }
}
