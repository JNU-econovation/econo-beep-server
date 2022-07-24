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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    private BookRentalRepository bookRentalRepository;

    @Autowired
    private BookCoverImageRepository bookCoverImageRepository;

    @Autowired
    @InjectMocks
    private BookRentalService bookRentalService;
    @MockBean private UserApi userApi;


    private final Long NOT_FOUND_BOOK_ID = 100L;
    private final String SAMPLE_PINCODE = "1234";

    private static final String TEST_ABSOLUTE_PATH = new File("").getAbsolutePath() + "/src/test/java/com/econo/econobeepserver/";
    private static final String TEST_IMAGE_PATH = TEST_ABSOLUTE_PATH + "images/testImage.jpg";
    private static final String UPDATE_TEST_IMAGE_PATH = TEST_ABSOLUTE_PATH + "images/testImageUpdate.jpg";


    @AfterEach
    void reset() {
        bookRepository.deleteAll();
    }

    private BookSaveDto newBookSaveDto() {
        try {
            return BookSaveDto.builder()
                    .title("testBook")
                    .type(RenteeType.APP)
                    .authorName("testAuthor")
                    .publisherName("testPublisher")
                    .publishedDateEpochSecond(toEpochSecond(LocalDate.of(1999, 10, 18)))
                    .bookCoverImage(new MockMultipartFile("testImage.jpg", "testImage.jpg", "image/jpg", new FileInputStream(TEST_IMAGE_PATH)))
                    .note("test")
                    .build();
        } catch (IOException e) {
            return null;
        }
    }

    private BookSaveDto updatedBookSaveDto() {
        try {
            return BookSaveDto.builder()
                    .title("testBookUpdate")
                    .type(RenteeType.APP)
                    .authorName("testAuthorUpdate")
                    .publisherName("testPublisherUpdate")
                    .publishedDateEpochSecond(toEpochSecond(LocalDate.of(2022, 10, 18)))
                    .bookCoverImage(new MockMultipartFile("testImageUpdate.jpg", "testImageUpdate.jpg", "image/jpg", new FileInputStream(UPDATE_TEST_IMAGE_PATH)))
                    .note("testUpdate")
                    .build();
        } catch (IOException e) {
            return null;
        }
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
        assertNotNull(savedBook.getBookCoverImage());
        assertEquals(newBookSaveDto.getNote(), savedBook.getNote());

        assertEquals(1L, bookCoverImageRepository.count());
        BookCoverImage bookCoverImage = bookCoverImageRepository.findAll().get(0);
        assertEquals(bookId, bookCoverImage.getBook().getId());
    }


    @DisplayName("updateBookById 작동 테스트")
    @Test
    void test_updateBookById() {
        // given
        BookSaveDto newBookSaveDto = newBookSaveDto();
        long bookId = bookService.createBook(newBookSaveDto);
        String oldBookCoverFilePath = bookService.getBookById(bookId).getBookCoverImage().getFilePath();
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
        assertNotEquals(oldBookCoverFilePath, savedBook.getBookCoverImage().getFilePath());
        assertEquals(updatedBookSaveDto.getNote(), savedBook.getNote());

        assertEquals(1L, bookCoverImageRepository.count());
        BookCoverImage bookCoverImage = bookCoverImageRepository.findAll().get(0);
        assertEquals(bookId, bookCoverImage.getBook().getId());
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
