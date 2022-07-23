package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Book.BookCoverImageRepository;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.domain.User.UserApi;
import com.econo.econobeepserver.dto.Book.BookSaveDto;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.Book.BookRentalService;
import com.econo.econobeepserver.service.Book.BookService;
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

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceCUDTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private BookRentalService bookRentalService;

    @MockBean
    private UserApi userApi;

    @Autowired
    private BookRentalRepository bookRentalRepository;

    @Autowired
    private BookCoverImageRepository bookCoverImageRepository;


    private final Long NOT_FOUND_BOOK_ID = 100L;

    private final String SAMPLE_PINCODE = "1234";


    @AfterEach
    void reset() {
        bookRepository.deleteAll();
    }



    private BookSaveDto newBookSaveDto() {
        Book book = Book.builder()
                .bookCoverImage(
                        BookCoverImage.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testBook")
                .type(RenteeType.APP)
                .authorName("testAuthor")
                .publisherName("testPublisher")
                .publishedDate(LocalDate.of(1999, 10, 18))
                .note("test")
                .build();

        return new BookSaveDto(book);
    }

    private BookSaveDto updatedBookSaveDto() {
        Book book = Book.builder()
                .bookCoverImage(
                        BookCoverImage.builder()
                                .filePath("images/testImageUpdate.jpg")
                                .build()
                )
                .title("testBookUpdate")
                .type(RenteeType.APP)
                .authorName("testAuthorUpdate")
                .publisherName("testPublisherUpdate")
                .publishedDate(LocalDate.of(2022, 10, 18))
                .note("testUpdate")
                .build();

        return new BookSaveDto(book);
    }

    @DisplayName("createBook 작동 테스트")
    @Test
    void test_createBook() {
        // given
        BookSaveDto newBookSaveDto = newBookSaveDto();

        // when
        long bookId = bookService.createBook(newBookSaveDto);

        // then
        assertEquals(1, bookRepository.count());

        Book savedBook = bookRepository.findById(bookId).get();
        assertEquals(newBookSaveDto.getTitle(), savedBook.getTitle());
        assertEquals(newBookSaveDto.getType(), savedBook.getType());
        assertEquals(newBookSaveDto.getAuthorName(), savedBook.getAuthorName());
        assertEquals(newBookSaveDto.getPublisherName(), savedBook.getPublisherName());
        assertEquals(newBookSaveDto.getPublishedDateEpochSecond(), toEpochSecond(savedBook.getPublishedDate()));
        assertEquals(newBookSaveDto.getNote(), savedBook.getNote());

        // TODO : Add assert to compare BookCoverImage
    }


    @DisplayName("updateBookById 작동 테스트")
    @Test
    void test_updateBookById() {
        // given
        BookSaveDto newBookSaveDto = newBookSaveDto();
        long bookId = bookService.createBook(newBookSaveDto);
        BookSaveDto updatedBookSaveDto = updatedBookSaveDto();

        // when
        bookService.updateBookById(bookId, updatedBookSaveDto());

        // then
        Book savedBook = bookRepository.findById(bookId).get();
        assertEquals(updatedBookSaveDto.getTitle(), savedBook.getTitle());
        assertEquals(updatedBookSaveDto.getType(), savedBook.getType());
        assertEquals(updatedBookSaveDto.getAuthorName(), savedBook.getAuthorName());
        assertEquals(updatedBookSaveDto.getPublisherName(), savedBook.getPublisherName());
        assertEquals(updatedBookSaveDto.getPublishedDateEpochSecond(), toEpochSecond(savedBook.getPublishedDate()));
        assertEquals(updatedBookSaveDto.getNote(), savedBook.getNote());

        // TODO : Add assert to compare BookCoverImage
    }


    @DisplayName("updateBookById 오류 대응 테스트 (not exist id params)")
    @Test
    void test_updateBookById_notExistIdParams() {
        // given
        bookService.createBook(newBookSaveDto());


        // when & then
        assertThrows(NotFoundRenteeException.class, () -> {bookService.updateBookById(NOT_FOUND_BOOK_ID, updatedBookSaveDto());});
    }


    private UserInfoDto sampleUser() {
        return UserInfoDto.builder()
                .uid(1L)
                .name("tester")
                .pinCode("1234")
                .build();
    }

    @DisplayName("deleteBookById 작동 테스트")
    @Test
    @Transactional
    void test_deleteBookById() {
        // given
        long bookId = bookService.createBook(newBookSaveDto());
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);
        bookRentalService.rentBookById(bookId, SAMPLE_PINCODE);

        // when
        bookService.deleteBookById(bookId);;

        // then
        assertTrue(bookRepository.findById(bookId).isEmpty());
        assertEquals(0L, bookCoverImageRepository.count());
        assertEquals(0L, bookRentalRepository.count());
    }

    @DisplayName("deleteBookById 오류 대응 테스트 (not exist id params)")
    @Test
    void test_deleteBookById_notExistIdParams() {
        // when & then
        assertThrows(NotFoundRenteeException.class, () -> {bookService.deleteBookById(NOT_FOUND_BOOK_ID);});
    }
}
