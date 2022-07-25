package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.BookRental.BookRental;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.User.UserApi;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import com.econo.econobeepserver.exception.UnreturnableException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class BookRentalService {

    private final BookService bookService;
    private UserApi userApi;


    private void validateRentableRentee(final Book book) {
        if (book.getRentState() != RentState.RENTABLE) {
            throw new UnrentableException();
        }
    }

    @Transactional
    public void rentBookById(Long id, String pinCode) {
        userApi.validatePinCode(pinCode);

        Book book = bookService.getBookById(id);
        validateRentableRentee(book);

        UserInfoDto userInfoDto = userApi.getUserInfoDtoByPinCode(pinCode);
        BookRental bookRental = BookRental.builder()
                .renterId(userInfoDto.getUid())
                .renterName(userInfoDto.getName())
                .build();

        book.rentBook(bookRental);
    }


    private void validateReturnableRentee(final Book book) {
        if (book.getRentalHistories().isEmpty() || book.getRentState() != RentState.RENTED) {
            throw new UnreturnableException();
        }
    }

    private void validateRenter(final BookRental bookRental, final Long renterId) {
        if (
                !Objects.equals(bookRental.getRenterId(), renterId)
        ) {
            throw new NotRenterException();
        }
    }

    @Transactional
    public void returnBookById(Long id, String pinCode) {
        userApi.validatePinCode(pinCode);

        Book book = bookService.getBookById(id);
        validateReturnableRentee(book);

        BookRental bookRental = book.getRentalHistories().get(book.getRentalHistories().size() - 1);
        UserInfoDto userInfoDto = userApi.getUserInfoDtoByPinCode(pinCode);
        validateRenter(bookRental, userInfoDto.getUid());

        bookRental.returnBook();
        book.returnBook();
    }
}
