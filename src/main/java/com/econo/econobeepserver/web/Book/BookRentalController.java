package com.econo.econobeepserver.web.Book;

import com.econo.econobeepserver.service.Book.BookRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookRentalController {

    private final BookRentalService bookRentalService;

    @PutMapping("/book/{id}/rent")
    public ResponseEntity<String> rentBookById(@PathVariable(value = "id") Long id,
                                               @RequestParam(value = "pinCode") String pinCode) {
        bookRentalService.rentBookById(id, pinCode);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/book/{id}/return")
    public ResponseEntity<String> returnBookById(@PathVariable(value = "id") Long id,
                                                 @RequestParam(value = "pinCode") String pinCode) {
        bookRentalService.returnBookById(id, pinCode);

        return ResponseEntity.ok().build();
    }

}
