package com.econo.econobeepserver.web.Book;

import com.econo.econobeepserver.domain.Book.BookType;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<BookInfoDto> getBookInfoDtoById(@PathVariable(value = "id") Long id) {
        Optional<BookInfoDto> bookInfoDto = bookService.getBookInfoDtoById(id);
        if (bookInfoDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bookInfoDto.get());
    }

    @GetMapping("/book/list/all")
    public ResponseEntity<List<BookElementDto>> getBookElementDtosWithPaging(@RequestParam(value = "pageSize") int pageSize,
                                                                             @RequestParam(value = "lastBookId", required = false, defaultValue = "0") Long lastId) {
        List<BookElementDto> bookElementDtos = bookService.getBookElementDtosWithPaging(pageSize, lastId);
        return ResponseEntity.ok(bookElementDtos);
    }

    @GetMapping("/book/list/{type}")
    public ResponseEntity<List<BookElementDto>> getBookElementDtosByBookTypeWithPaging(@PathVariable(value = "type") BookType bookType,
                                                                                       @RequestParam(value = "pageSize") int pageSize,
                                                                                       @RequestParam(value = "lastBookId", required = false, defaultValue = "0") Long lastId) {
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
