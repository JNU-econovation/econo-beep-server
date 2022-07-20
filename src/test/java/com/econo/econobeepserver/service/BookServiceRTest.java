package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.dto.Book.BookElementDto;
import com.econo.econobeepserver.dto.Book.BookInfoDto;
import com.econo.econobeepserver.dto.Book.BookManagementInfoDto;
import com.econo.econobeepserver.exception.ExceptionMessage;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.Book.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BookServiceRTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;


    private Optional<Book> successfulResponseOfFindById() {
        return Optional.of(
                Book.builder()
                        .id(1L)
                        .bookCoverImage(
                                BookCoverImage.builder()
                                        .filePath("images/testImage.jpg")
                                        .build()
                        )
                        .title("testBook")
                        .type(RenteeType.WEB)
                        .authorName("testAuthor")
                        .publisherName("testPublisher")
                        .publishedDate(LocalDate.of(1999, 10, 18))
                        .note("test")
                        .build()
        );
    }

    private Optional<Book> failedResponseOfFindById() {
        return Optional.empty();
    }

    @DisplayName("getBookInfoDtoById 작동 테스트")
    @Test
    void test_getBookInfoDtoById() {
        // given
        Optional<Book> successfulResponse = successfulResponseOfFindById();
        doReturn(successfulResponse).when(bookRepository)
                .findById(1L);

        BookInfoDto wantedResult = new BookInfoDto(successfulResponse.get());

        // when & then
        assertDoesNotThrow(() -> {
            BookInfoDto result = bookService.getBookInfoDtoById(1L);
            assertTrue(new ReflectionEquals(wantedResult).matches(result));
        });
    }

    @DisplayName("getBookInfoDtoById 오류 반응 테스트 (not exist id params)")
    @Test
    void test_getBookInfoDtoById_notExistIdParams() {
        // given
        Optional<Book> failedResponse = failedResponseOfFindById();
        doReturn(failedResponse).when(bookRepository)
                .findById(-1L);

        // when & then
        Exception exception = assertThrows(NotFoundRenteeException.class, () -> {
            bookService.getBookInfoDtoById(-1L);
        });
        assertEquals(ExceptionMessage.NOT_FOUND_RENTEE_EXCEPTION.getMessage(), exception.getMessage());
    }


    private List<Book> successfulResponseOfGetBookWithPaging() {
        return Arrays.asList(
                Book.builder()
                        .id(1L)
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
                        .note("test")
                        .build(),

                Book.builder()
                        .id(2L)
                        .bookCoverImage(
                                BookCoverImage.builder()
                                        .filePath("images/testImage.jpg")
                                        .build()
                        )
                        .title("testBook2")
                        .type(RenteeType.WEB)
                        .authorName("testAuthor2")
                        .publisherName("testPublisher2")
                        .publishedDate(LocalDate.of(1999, 10, 18))
                        .note("test")
                        .build()
        );
    }

    private List<Book> failedResponseOfGetBookWithPaging() {
        return Collections.emptyList();
    }

    @DisplayName("getBookElementDtosWithPaging 작동 테스트")
    @Test
    void test_getBookElementDtosWithPaging() {
        // given
        List<Book> successfulResponse = successfulResponseOfGetBookWithPaging();
        doReturn(successfulResponse).when(bookRepository)
                .getRecentBookWithPaging(2, null);

        List<BookElementDto> wantedResults = successfulResponse.stream().map(BookElementDto::new).collect(Collectors.toList());

        // when
        List<BookElementDto> results = bookService.getBookElementDtosWithPaging(2, null);

        // then
        assertIterableEquals(wantedResults, results);
    }

    @DisplayName("getBookElementDtosWithPaging 엣지케이스 테스트 (exceeded lastId params")
    @Test
    void test_getBookElementDtosWithPaging_exceededLastIdParams() {
        // given
        List<Book> failedResponse = failedResponseOfGetBookWithPaging();
        doReturn(failedResponse).when(bookRepository)
                .getRecentBookWithPaging(2, 10L);

        List<BookElementDto> wantedResults = failedResponse.stream().map(BookElementDto::new).collect(Collectors.toList());

        // when
        List<BookElementDto> results = bookService.getBookElementDtosWithPaging(2, 10L);

        // then
        assertIterableEquals(wantedResults, results);
    }


    @DisplayName("getBookElementDtosByBookTypeWithPaging 작동 테스트")
    @Test
    void test_getBookElementDtosByBookTypeWithPaging() {
        // given
        List<Book> successfulResponse = successfulResponseOfGetBookWithPaging();
        doReturn(successfulResponse).when(bookRepository)
                .getBookByTypeWithPaging(RenteeType.WEB, 2, null);

        List<BookElementDto> wantedResults = successfulResponse.stream().map(BookElementDto::new).collect(Collectors.toList());

        // when
        List<BookElementDto> results = bookService.getBookElementDtosByBookTypeWithPaging(RenteeType.WEB, 2, null);

        // then
        assertIterableEquals(wantedResults, results);
    }

    @DisplayName("getBookElementDtosByBookTypeWithPaging 엣지케이스 테스트 (exceeded lastId params")
    @Test
    void test_getBookElementDtosByBookTypeWithPaging_exceededLastIdParams() {
        // given
        List<Book> failedResponse = failedResponseOfGetBookWithPaging();
        doReturn(failedResponse).when(bookRepository)
                .getBookByTypeWithPaging(RenteeType.WEB, 2, 10L);

        List<BookElementDto> wantedResults = failedResponse.stream().map(BookElementDto::new).collect(Collectors.toList());

        // when
        List<BookElementDto> results = bookService.getBookElementDtosByBookTypeWithPaging(RenteeType.WEB, 2, 10L);

        // then
        assertIterableEquals(wantedResults, results);
    }


    private List<Book> successfulResponseOfSearchBookByKeyword() {
        return Arrays.asList(
                Book.builder()
                        .id(1L)
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
                        .note("test")
                        .build(),

                Book.builder()
                        .id(2L)
                        .bookCoverImage(
                                BookCoverImage.builder()
                                        .filePath("images/testImage.jpg")
                                        .build()
                        )
                        .title("testBook2")
                        .type(RenteeType.WEB)
                        .authorName("testAuthor2")
                        .publisherName("testPublisher2")
                        .publishedDate(LocalDate.of(1999, 10, 18))
                        .note("test")
                        .build()
        );
    }

    private List<Book> failedResponseOfSearchBookByKeyword() {
        return Collections.emptyList();
    }

    @DisplayName("searchBookElementDtosByKeyword 작동 테스트")
    @Test
    void test_searchBookElementDtosByKeyword() {
        // given
        List<Book> successfulResponse = successfulResponseOfSearchBookByKeyword();
        doReturn(successfulResponse).when(bookRepository)
                .searchBookByKeyword("test");

        List<BookElementDto> wantedResults = successfulResponse.stream().map(BookElementDto::new).collect(Collectors.toList());

        // when
        List<BookElementDto> results = bookService.searchBookElementDtosByKeyword("test");

        // then
        assertIterableEquals(wantedResults, results);
    }

    @DisplayName("searchBookElementDtosByKeyword 엣지케이스 테스트 (not exist keyword")
    @Test
    void test_searchBookElementDtosByKeyword_notExistKeyword() {
        // given
        List<Book> failedResponse = failedResponseOfSearchBookByKeyword();
        doReturn(failedResponse).when(bookRepository)
                .searchBookByKeyword("real");

        List<BookElementDto> wantedResults = failedResponse.stream().map(BookElementDto::new).collect(Collectors.toList());

        // when
        List<BookElementDto> results = bookService.searchBookElementDtosByKeyword("real");

        // then
        assertIterableEquals(wantedResults, results);
    }


    private List<Book> successfulResponseOfGetBookByIdDescWithPaging() {
        return Arrays.asList(
                Book.builder()
                        .id(1L)
                        .bookCoverImage(
                                BookCoverImage.builder()
                                        .filePath("images/testImage.jpg")
                                        .build()
                        )
                        .title("testBook2")
                        .type(RenteeType.WEB)
                        .authorName("testAuthor2")
                        .publisherName("testPublisher2")
                        .publishedDate(LocalDate.of(1999, 10, 18))
                        .note("test")
                        .build(),

                Book.builder()
                        .id(2L)
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
                        .note("test")
                        .build()
        );
    }

    private List<Book> failedResponseOfGetBookByIdDescWithPaging() {
        return Collections.emptyList();
    }

    @DisplayName("getBookManagementInfoDtosByIdDescWithPaging 작동 테스트")
    @Test
    void test_getBookManagementInfoDtosByIdDescWithPaging() {
        // given
        List<Book> successfulResponse = successfulResponseOfGetBookByIdDescWithPaging();
        doReturn(successfulResponse).when(bookRepository)
                .getRecentBookWithPaging(2, null);

        List<BookManagementInfoDto> wantedResults = successfulResponse.stream().map(BookManagementInfoDto::new).collect(Collectors.toList());

        // when
        List<BookManagementInfoDto> results = bookService.getBookManagementInfoDtosByIdDescWithPaging(2, null);

        // then
        assertIterableEquals(wantedResults, results);
    }

    @DisplayName("getBookManagementInfoDtosByIdDescWithPaging 엣지케이스 테스트 (exceeded lastId params)")
    @Test
    void test_getBookManagementInfoDtosByIdDescWithPaging_exceededLastIdParams() {
        // given
        List<Book> failedResponse = failedResponseOfGetBookByIdDescWithPaging();
        doReturn(failedResponse).when(bookRepository)
                .getRecentBookWithPaging(2, 10L);

        List<BookManagementInfoDto> wantedResults = failedResponse.stream().map(BookManagementInfoDto::new).collect(Collectors.toList());

        // when
        List<BookManagementInfoDto> results = bookService.getBookManagementInfoDtosByIdDescWithPaging(2, 10L);

        // then
        assertIterableEquals(wantedResults, results);
    }

    @DisplayName("searchBookManagementInfoDtosByKeyword 작동 테스트")
    @Test
    void test_searchBookManagementInfoDtosByKeyword() {
        // given
        List<Book> successfulResponse = successfulResponseOfSearchBookByKeyword();
        doReturn(successfulResponse).when(bookRepository)
                .searchBookByKeyword("test");

        List<BookManagementInfoDto> wantedResults = successfulResponse.stream().map(BookManagementInfoDto::new).collect(Collectors.toList());

        // when
        List<BookManagementInfoDto> results = bookService.searchBookManagementInfoDtosByKeyword("test");

        // then
        assertIterableEquals(wantedResults, results);
    }

    @DisplayName("searchBookManagementInfoDtosByKeyword 엣지케이스 테스트 (not exist keyword)")
    @Test
    void test_searchBookManagementInfoDtosByKeyword_notExistKeyword() {
        // given
        List<Book> failedResponse = failedResponseOfSearchBookByKeyword();
        doReturn(failedResponse).when(bookRepository)
                .searchBookByKeyword("real");

        List<BookManagementInfoDto> wantedResults = failedResponse.stream().map(BookManagementInfoDto::new).collect(Collectors.toList());

        // when
        List<BookManagementInfoDto> results = bookService.searchBookManagementInfoDtosByKeyword("real");

        // then
        assertIterableEquals(wantedResults, results);
    }
}
