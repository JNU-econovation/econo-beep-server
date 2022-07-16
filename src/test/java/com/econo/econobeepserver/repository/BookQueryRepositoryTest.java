package com.econo.econobeepserver.repository;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Book.BookQueryRepository;
import com.econo.econobeepserver.domain.RenteeType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class BookQueryRepositoryTest {

    @Autowired
    private BookQueryRepository bookQueryRepository;

    private final int pageSize = 2;

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

    @DisplayName("getBookWithPaging 작동 테스트 (기본, 1페이지)")
    @Test
    void test_getBookWithPaging() {
        // given

        final Long lastId = null;


        // when
        List<Book> books = bookQueryRepository.getBookWithPaging(pageSize, lastId);

        // then

    }
}
