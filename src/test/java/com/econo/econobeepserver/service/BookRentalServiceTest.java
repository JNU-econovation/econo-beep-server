package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.BookRental.BookRental;
import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.domain.User.UserApi;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotFoundPinCodeException;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.exception.WrongFormatPinCodeException;
import com.econo.econobeepserver.service.Book.BookRentalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookRentalServiceTest {

    @Autowired
    @InjectMocks
    private BookRentalService bookRentalService;

    @MockBean
    private UserApi userApi;

    @Autowired
    private BookRentalRepository bookRentalRepository;

    @Autowired
    private BookRepository bookRepository;


    private final String SAMPLE_PINCODE = "1234";

    private final String WRONG_CHARACTER_PINCODE = "1A34";

    private final String SHORT_LENGTH_PINCODE = "1";

    private final String LONG_LENGTH_PINCODE = "1234567";

    private final String NOT_FOUND_PINCODE = "9876";


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

    private UserInfoDto sampleUser() {
        return UserInfoDto.builder()
                .uid(1L)
                .name("tester")
                .pinCode("1234")
                .build();
    }

    @AfterEach
    void reset() {
        bookRentalRepository.deleteAll();
        bookRepository.deleteAll();
    }


    @DisplayName("rentBookById 작동 테스트")
    @Test
    @Transactional
    void test_rentBookById() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);


        // when & then
        Book book = bookRepository.findById(bookId).get();

        assertDoesNotThrow(() -> bookRentalService.rentBookById(bookId, SAMPLE_PINCODE));
        assertEquals(RentState.RENTED, book.getRentState());

        assertEquals(1L, bookRentalRepository.count());

        assertEquals(1, book.getRentalHistories().size());
        assertEquals(1, book.getRentCount());
    }


    @DisplayName("rentBookById 오류 대응 테스트 (존재하지 않는 BookId)")
    @Test
    void test_rentBookById_notExistBookIdParams() {
        // given
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);


        // when & then
        assertThrows(NotFoundRenteeException.class, () -> bookRentalService.rentBookById(100L, SAMPLE_PINCODE));
    }


    @DisplayName("rentBookById 오류 대응 테스트 (글자가 포함된 핀코드)")
    @Test
    void test_rentBookById_wrongCharacterPinCodeParams() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(WRONG_CHARACTER_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> bookRentalService.rentBookById(bookId, WRONG_CHARACTER_PINCODE));
    }


    @DisplayName("rentBookById 오류 대응 테스트 (길이가 짧은 핀코드)")
    @Test
    void test_rentBookById_shortLengthPinCodeParams() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SHORT_LENGTH_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> bookRentalService.rentBookById(bookId, SHORT_LENGTH_PINCODE));
    }


    @DisplayName("rentBookById 오류 대응 테스트 (길이가 긴 핀코드)")
    @Test
    void test_rentBookById_longLengthPinCodeParams() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(LONG_LENGTH_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> bookRentalService.rentBookById(bookId, LONG_LENGTH_PINCODE));
    }


    @DisplayName("rentBookById 오류 대응 테스트 (존재하지 않는 핀코드 예외)")
    @Test
    void test_rentBookById_notFoundPinCode() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();
        doThrow(NotFoundPinCodeException.class)
                .when(userApi)
                .getUserInfoDtoByPinCode(NOT_FOUND_PINCODE);


        // when & then
        assertThrows(NotFoundPinCodeException.class, () -> bookRentalService.rentBookById(bookId, NOT_FOUND_PINCODE));
    }


    @DisplayName("returnBookById 작동 테스트")
    @Test
    @Transactional
    void test_returnBookById() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);

        assertDoesNotThrow(() -> bookRentalService.rentBookById(bookId, SAMPLE_PINCODE));


        // when & then
        Book book = bookRepository.findById(bookId).get();

        assertDoesNotThrow(() -> bookRentalService.returnBookById(bookId, SAMPLE_PINCODE));
        assertEquals(RentState.RENTABLE, book.getRentState());

        assertEquals(1L, bookRentalRepository.count());
        BookRental bookRental = bookRentalRepository.findAll().get(0);
        assertNotNull(bookRental.getReturnDateTime());

        assertEquals(1, book.getRentalHistories().size());
    }


    @DisplayName("returnBookById 오류 대응 테스트 (존재하지 않는 BookId)")
    @Test
    void test_returnBookById_notExistBookIdParams() {
        // given


        // when & then
        assertThrows(NotFoundRenteeException.class, () -> bookRentalService.returnBookById(100L, SAMPLE_PINCODE));
    }


    @DisplayName("returnBookById 오류 대응 테스트 (글자가 포함된 핀코드)")
    @Test
    void test_returnBookById_wrongCharacterPinCodeParams() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> bookRentalService.returnBookById(bookId, WRONG_CHARACTER_PINCODE));
    }


    @DisplayName("returnBookById 오류 대응 테스트 (길이가 짧은 핀코드)")
    @Test
    void test_returnBookById_shortLengthPinCodeParams() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> bookRentalService.returnBookById(bookId, SHORT_LENGTH_PINCODE));
    }


    @DisplayName("returnBookById 오류 대응 테스트 (길이가 긴 핀코드)")
    @Test
    void test_returnBookById_longLengthPinCodeParams() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> bookRentalService.returnBookById(bookId, LONG_LENGTH_PINCODE));
    }


    @DisplayName("returnBookById 오류 대응 테스트 (존재하지 않는 핀코드 예외)")
    @Test
    @Transactional
    void test_returnBookById_notFoundPinCode() {
        // given
        long bookId = bookRepository.save(sampleBook()).getId();

        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);
        bookRentalService.rentBookById(bookId, SAMPLE_PINCODE);

        doThrow(NotFoundPinCodeException.class)
                .when(userApi)
                .getUserInfoDtoByPinCode(NOT_FOUND_PINCODE);


        // when & then
        assertThrows(NotFoundPinCodeException.class, () -> bookRentalService.returnBookById(bookId, NOT_FOUND_PINCODE));
    }

}
