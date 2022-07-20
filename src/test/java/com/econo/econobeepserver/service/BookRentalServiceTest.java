package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.BookRental.BookRental;
import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.exception.NotFoundPinCodeException;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.exception.WrongFormatPinCodeException;
import com.econo.econobeepserver.service.Book.BookRentalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookRentalServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRentalService bookRentalService;

    @Autowired
    private BookRentalRepository bookRentalRepository;

    private final long BOOK_ID = 1L;

    private final String SAMPLE_PINCODE = "1234";

    private final String WRONG_CHARACTER_PINCODE = "1A34";

    private final String SHORT_LENGTH_PINCODE = "1";

    private final String LONG_LENGTH_PINCODE = "1234567";

    private final String NOT_FOUND_PINCODE = "1234";


    private Book sampleBook() {
        return Book.builder()
                .bookCoverImage(
                        BookCoverImage.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testBook1")
                .type(RenteeType.WEB)
                .authorName("testAuthor1")
                .publisherName("testPublisher1")
                .publishedDate(LocalDate.of(1999, 10, 18))
                .note("test1")
                .build();
    }

    @AfterEach
    void reset() {
        bookRentalRepository.deleteAll();
        bookRepository.deleteAll();
    }


    @DisplayName("rentBookById 작동 테스트")
    @Test
    void test_rentBookById() {
        // given
        bookRepository.save(sampleBook());


        // when & then
        assertDoesNotThrow(() -> {
            bookRentalService.rentBookById(BOOK_ID, SAMPLE_PINCODE);
        });

        Optional<Book> book = bookRepository.findById(BOOK_ID);
        assertTrue(book.isPresent());
        assertEquals(RentState.RENTED, book.get().getRentState());

        Optional<BookRental> bookRental = bookRentalRepository.findById(BOOK_ID);
        assertTrue(bookRental.isPresent());

        assertEquals(1, book.get().getRentalHistories().size());
        assertEquals(BOOK_ID, book.get().getRentalHistories().get(0).getId());
        assertNotNull(bookRental.get().getBook());
        assertEquals(BOOK_ID, bookRental.get().getBook().getId());
    }


    @DisplayName("rentBookById 오류 대응 테스트 (존재하지 않는 BookId)")
    @Test
    void test_rentBookById_notExistBookIdParams() {
        // when & then
        assertThrows(NotFoundRenteeException.class, () -> {
            bookRentalService.rentBookById(BOOK_ID, SAMPLE_PINCODE);
        });
    }


    @DisplayName("rentBookById 오류 대응 테스트 (글자가 포함된 핀코드)")
    @Test
    void test_rentBookById_wrongCharacterPinCodeParams() {
        // given
        bookRepository.save(sampleBook());

        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> {
            bookRentalService.rentBookById(BOOK_ID, WRONG_CHARACTER_PINCODE);
        });
    }


    @DisplayName("rentBookById 오류 대응 테스트 (길이가 짧은 핀코드)")
    @Test
    void test_rentBookById_shortLengthPinCodeParams() {
        // given
        bookRepository.save(sampleBook());

        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> {
            bookRentalService.rentBookById(BOOK_ID, SHORT_LENGTH_PINCODE);
        });
    }


    @DisplayName("rentBookById 오류 대응 테스트 (길이가 긴 핀코드)")
    @Test
    void test_rentBookById_longLengthPinCodeParams() {
        // given
        bookRepository.save(sampleBook());

        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> {
            bookRentalService.rentBookById(BOOK_ID, LONG_LENGTH_PINCODE);
        });
    }


    @DisplayName("rentBookById 오류 대응 테스트 (존재하지 않는 핀코드 예외)")
    @Test
    void test_rentBookById_notFoundPinCode() {
        // given
        bookRepository.save(sampleBook());

        // when & then
        assertThrows(NotFoundPinCodeException.class, () -> {
            bookRentalService.rentBookById(BOOK_ID, NOT_FOUND_PINCODE);
        });
    }


    @DisplayName("returnBookById 작동 테스트")
    @Test
    void test_returnBookById() {
        // given
        bookRepository.save(sampleBook());


        // when & then
        assertDoesNotThrow(() -> bookRentalService.returnBookById(BOOK_ID, SAMPLE_PINCODE));

        Optional<Book> book = bookRepository.findById(BOOK_ID);
        assertTrue(book.isPresent());
        assertEquals(RentState.RENTABLE, book.get().getRentState());

        Optional<BookRental> bookRental = bookRentalRepository.findById(BOOK_ID);
        assertTrue(bookRental.isPresent());
        assertNotNull(bookRental.get().getReturnDateTime());


        assertEquals(1, book.get().getRentalHistories().size());
        assertEquals(BOOK_ID, book.get().getRentalHistories().get(0).getId());
        assertNotNull(bookRental.get().getBook());
        assertEquals(BOOK_ID, bookRental.get().getBook().getId());
    }


    @DisplayName("returnBookById 오류 대응 테스트 (존재하지 않는 BookId)")
    @Test
    void test_returnBookById_notExistBookIdParams() {
        // when & then
        assertThrows(NotFoundRenteeException.class, () -> {
            bookRentalService.returnBookById(BOOK_ID, SAMPLE_PINCODE);
        });
    }


    @DisplayName("returnBookById 오류 대응 테스트 (글자가 포함된 핀코드)")
    @Test
    void test_returnBookById_wrongCharacterPinCodeParams() {
        // given
        bookRepository.save(sampleBook());

        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> {
            bookRentalService.returnBookById(BOOK_ID, WRONG_CHARACTER_PINCODE);
        });
    }


    @DisplayName("returnBookById 오류 대응 테스트 (길이가 짧은 핀코드)")
    @Test
    void test_returnBookById_shortLengthPinCodeParams() {
        // given
        bookRepository.save(sampleBook());

        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> {
            bookRentalService.returnBookById(BOOK_ID, SHORT_LENGTH_PINCODE);
        });
    }


    @DisplayName("returnBookById 오류 대응 테스트 (길이가 긴 핀코드)")
    @Test
    void test_returnBookById_longLengthPinCodeParams() {
        // given
        bookRepository.save(sampleBook());

        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> {
            bookRentalService.returnBookById(BOOK_ID, LONG_LENGTH_PINCODE);
        });
    }


    @DisplayName("returnBookById 오류 대응 테스트 (존재하지 않는 핀코드 예외)")
    @Test
    void test_returnBookById_notFoundPinCode() {
        // given
        bookRepository.save(sampleBook());

        // when & then
        assertThrows(NotFoundPinCodeException.class, () -> {
            bookRentalService.returnBookById(BOOK_ID, NOT_FOUND_PINCODE);
        });
    }

}
