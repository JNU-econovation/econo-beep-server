package com.econo.econobeepserver.repository;

import com.econo.econobeepserver.TestConfig;
import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.RenteeType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(TestConfig.class)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private final int PAGE_SIZE = 2;


    private Book sampleBook1() {
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

    private Book sampleBook2() {
        return Book.builder()
                .bookCoverImage(
                        BookCoverImage.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testBook2")
                .type(RenteeType.WEB)
                .authorName("testAuthor2")
                .publisherName("testPublisher2")
                .publishedDate(LocalDate.of(2000, 10, 18))
                .note("test2")
                .build();
    }

    private Book sampleBook3() {
        return Book.builder()
                .bookCoverImage(
                        BookCoverImage.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testBook3")
                .type(RenteeType.WEB)
                .authorName("testAuthor3")
                .publisherName("testPublisher3")
                .publishedDate(LocalDate.of(2001, 10, 18))
                .note("test3")
                .build();
    }

    private Book sampleBook4() {
        return Book.builder()
                .bookCoverImage(
                        BookCoverImage.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testBook4")
                .type(RenteeType.APP)
                .authorName("testAuthor4")
                .publisherName("testPublisher4")
                .publishedDate(LocalDate.of(2002, 10, 18))
                .note("test4")
                .build();
    }

    private Book sampleBook5() {
        return Book.builder()
                .bookCoverImage(
                        BookCoverImage.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testBook5")
                .type(RenteeType.APP)
                .authorName("testAuthor5")
                .publisherName("testPublisher5")
                .publishedDate(LocalDate.of(2003, 10, 18))
                .note("test5")
                .build();
    }

    @BeforeAll
    void init() {
        bookRepository.save(sampleBook4());
        bookRepository.save(sampleBook5());
        bookRepository.save(sampleBook1());
        bookRepository.save(sampleBook2());
        bookRepository.save(sampleBook3());
    }



    @DisplayName("getRecentBookWithPaging 작동 테스트 (기본, 1페이지)")
    @Test
    void test_getRecentBookWithPaging_firstPage() {
        // given
        final Long lastId = null;

        // when
        List<Book> books = bookRepository.getRecentBookWithPaging(PAGE_SIZE, lastId);

        // then
        assertThat(books)
                .hasSize(2)
                .extracting(Book::getTitle)
                .containsExactly("testBook3", "testBook2");
    }

    @DisplayName("getRecentBookWithPaging 작동 테스트 (마지막, 3페이지)")
    @Test
    void test_getRecentBookWithPaging_lastPage() {
        // given
        final Long lastId = 2L;

        // when
        List<Book> books = bookRepository.getRecentBookWithPaging(PAGE_SIZE, lastId);

        // then
        assertThat(books)
                .hasSize(1)
                .extracting(Book::getTitle)
                .containsExactly("testBook4");
    }

    @DisplayName("getBookByTypeWithPaging 작동 테스트 (기본, 1페이지)")
    @Test
    void test_getBookByTypeWithPaging_firstPage() {
        // given
        final Long lastId = null;
        final RenteeType renteeType = RenteeType.WEB;

        // when
        List<Book> books = bookRepository.getBookByTypeWithPaging(renteeType, PAGE_SIZE, lastId);

        // then
        assertThat(books)
                .hasSize(2)
                .extracting(Book::getType)
                .contains(RenteeType.WEB);
    }

    @DisplayName("getBookByTypeWithPaging 작동 테스트 (마지막, 3페이지)")
    @Test
    void test_getBookByTypeWithPaging_lastPage() {
        // given
        final Long lastId = 4L;
        final RenteeType renteeType = RenteeType.WEB;

        // when
        List<Book> books = bookRepository.getBookByTypeWithPaging(renteeType, PAGE_SIZE, lastId);

        // then
        assertThat(books)
                .hasSize(1)
                .extracting(Book::getType)
                .contains(RenteeType.WEB);
    }

    @DisplayName("searchBookByKeyword 작동 테스트 (불완전한 키워드)")
    @Test
    void test_searchBookByKeyword_uncompletedKeyword() {
        // given
        final String keyword = "test";

        // when
        List<Book> books = bookRepository.searchBookByKeyword(keyword);

        // then
        assertThat(books)
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("testBook1", "testBook2", "testBook3", "testBook4", "testBook5");
    }

    @DisplayName("searchBookByKeyword 작동 테스트 (완전한 키워드)")
    @Test
    void test_searchBookByKeyword_completeKeyword() {
        // given
        final String keyword = "testBook1";

        // when
        List<Book> books = bookRepository.searchBookByKeyword(keyword);

        // then
        assertThat(books)
                .hasSize(1)
                .extracting(Book::getTitle)
                .containsExactlyInAnyOrder("testBook1");
    }
}
