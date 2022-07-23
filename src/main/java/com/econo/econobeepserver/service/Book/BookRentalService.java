package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.BookRental.BookRental;
import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.User.UserApi;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import com.econo.econobeepserver.exception.UnreturnableException;
import com.econo.econobeepserver.exception.WrongFormatPinCodeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class BookRentalService {

    private final BookService bookService;
    private UserApi userApi;

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void validatePinCode(String pinCode) {
        if (
                pinCode.length() != 4 || !isNumeric(pinCode)
        ) {
            throw new WrongFormatPinCodeException();
        }
    }

    private void validateRentableRentee(final Book book) {
        if (book.getRentState() != RentState.RENTABLE) {
            throw new UnrentableException();
        }
    }

    @Transactional
    public void rentBookById(Long id, String pinCode) {
        validatePinCode(pinCode);

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
        if (book.getRentalHistories().isEmpty()) {
            throw new UnreturnableException();
        }
    }

    private void validateRenter(final Book book, final BookRental bookRental, final Long renterId) {
        if (
                !Objects.equals(bookRental.getRenterId(), renterId) || book.getRentState() != RentState.RENTED
        ) {
            throw new NotRenterException();
        }
    }

    @Transactional
    public void returnBookById(Long id, String pinCode) {
        validatePinCode(pinCode);

        Book book = bookService.getBookById(id);
        validateReturnableRentee(book);
        BookRental bookRental = book.getRentalHistories().get(book.getRentalHistories().size() - 1);
        bookRental.returnBook();

        UserInfoDto userInfoDto = userApi.getUserInfoDtoByPinCode(pinCode);
        validateRenter(book, bookRental, userInfoDto.getUid());

        book.returnBook();
    }
}
