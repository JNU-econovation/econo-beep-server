package com.econo.econobeepserver.web.Book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRentalController {

    @PutMapping("/book/{id}/rent")
    public ResponseEntity<Void> rentBookByBookId(@PathVariable(value = "id") Long bookId) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/book/{id}/return")
    public ResponseEntity<Void> returnBookByBookId(@PathVariable(value = "id") Long bookId) {
        return ResponseEntity.ok(null);
    }

}
