package com.econo.econobeepserver.web.Book;

import com.econo.econobeepserver.exception.AlreadyRentedException;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import com.econo.econobeepserver.service.Book.BookRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookRentalController {

    private final BookRentalService bookRentalService;

    @PutMapping("/book/{id}/rent")
    public ResponseEntity<String> rentBookById(@PathVariable(value = "id") Long id) {
        try {
            bookRentalService.rentBookById(id);
            return ResponseEntity.ok().build();

        } catch (NotFoundRenteeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (AlreadyRentedException | UnrentableException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/book/{id}/return")
    public ResponseEntity<String> returnBookById(@PathVariable(value = "id") Long id) {
        try {
            bookRentalService.returnBookById(id);
            return ResponseEntity.ok().build();

        } catch (NotFoundRenteeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (NotRenterException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
