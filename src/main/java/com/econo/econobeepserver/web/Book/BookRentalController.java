package com.econo.econobeepserver.web.Book;

import com.econo.econobeepserver.service.Book.BookRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookRentalController {

    private final BookRentalService bookRentalService;

    @PutMapping("/book/{id}/rent")
    public ResponseEntity<Void> rentBookByBookId(@PathVariable(value = "id") Long bookId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/book/{id}/return")
    public ResponseEntity<Void> returnBookByBookId(@PathVariable(value = "id") Long bookId) {
        return ResponseEntity.ok(null);
    }

}
