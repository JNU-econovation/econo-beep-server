package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.dto.Book.BookSaveDto;
import com.econo.econobeepserver.exception.ExceptionMessage;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.Book.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BookServiceCUDTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;




    // TODO : Impl createBook Integration test code




    //TODO : Impl updateBookById Integration test code

    private BookSaveDto newBookSaveDto() {
        Book book = Book.builder()
                .bookCoverImage(
                        BookCoverImage.builder()
                                .filePath("images/testImage.jpg")
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

    private Optional<Book> failedResponseOfFindById() {
        return Optional.empty();
    }

    @DisplayName("updateBookById 오류 대응 테스트 (not exist id params)")
    @Test
    void test_updateBookById_notExistIdParams() {
        // given
        Optional<Book> failedResponse = failedResponseOfFindById();
        doReturn(failedResponse).when(bookRepository)
                .findById(-1L);

        BookSaveDto newBookData = newBookSaveDto();

        // when & then
        Exception exception = assertThrows(NotFoundRenteeException.class, () -> {bookService.updateBookById(-1L, newBookData);});
        assertEquals(ExceptionMessage.NOT_FOUND_RENTEE_EXCEPTION.getMessage(), exception.getMessage());
    }



    //TODO : Impl deleteBookById Integration test code

    @DisplayName("deleteBookById 오류 대응 테스트 (not exist id params)")
    @Test
    void test_deleteBookById_notExistIdParams() {
        // when & then
        Exception exception = assertThrows(NotFoundRenteeException.class, () -> {bookService.deleteBookById(-1L);});
        assertEquals(ExceptionMessage.NOT_FOUND_RENTEE_EXCEPTION.getMessage(), exception.getMessage());
    }
}
