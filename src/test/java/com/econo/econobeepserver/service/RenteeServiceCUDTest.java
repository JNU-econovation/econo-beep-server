package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Rentee.*;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import com.econo.econobeepserver.util.EpochTime;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static com.econo.econobeepserver.service.ImageHandlerTest.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RenteeServiceCUDTest {

    @Autowired
    private RenteeService renteeService;

    @Autowired
    private RenteeRepository renteeRepository;

    @Autowired
    private RenteeThumbnailRepository renteeThumbnailRepository;


    private RenteeSaveDto book1SaveDto;
    private RenteeSaveDto book2SaveDto;

    public RenteeServiceCUDTest() throws IOException {
        MockMultipartFile thumbnail = new MockMultipartFile(TEST_JPG_NAME, TEST_JPG_NAME, "image/jpg", new FileInputStream(TEST_JPG_PATH));
        MockMultipartFile updateThumbnail = new MockMultipartFile(UPDATE_TEST_JPG_NAME, UPDATE_TEST_JPG_NAME, "image/jpg", new FileInputStream(UPDATE_TEST_JPG_PATH));
        book1SaveDto = RenteeSaveDto.builder()
                .thumbnail(thumbnail)
                .type(RenteeType.BOOK)
                .name("book1")
                .bookArea(BookArea.APP)
                .bookAuthorName("author1")
                .bookPublisherName("publisher1")
                .bookPublishedDateEpochSecond(1640998861L)
                .note("note1")
                .build();
        book2SaveDto = RenteeSaveDto.builder()
                .thumbnail(updateThumbnail)
                .type(RenteeType.BOOK)
                .name("book2")
                .bookArea(BookArea.APP)
                .bookAuthorName("author2")
                .bookPublisherName("publisher2")
                .bookPublishedDateEpochSecond(1640998861L)
                .note("note2")
                .build();
    }


    @DisplayName("createRentee 정상동작 테스트")
    @Test
    void test_createRentee() {
        // when
        renteeService.create(book1SaveDto);

        // then
        Rentee book = renteeService.getRenteeByRenteeName(book1SaveDto.getName());

        assertEquals(book1SaveDto.getType(), book.getType());
        assertEquals(book1SaveDto.getName(), book.getName());
        assertEquals(book1SaveDto.getBookArea(), book.getBookArea());
        assertEquals(book1SaveDto.getBookAuthorName(), book.getBookAuthorName());
        assertEquals(book1SaveDto.getBookPublisherName(), book.getBookPublisherName());
        assertEquals(EpochTime.toLocalDate(book1SaveDto.getBookPublishedDateEpochSecond()), book.getBookPublishedDate());
        assertEquals(book1SaveDto.getNote(), book.getNote());
        assertEquals(RentState.RENTABLE, book.getRentState());
        assertEquals(0, book.getRentCount());

        long bookThumbnailId = book.getThumbnail().getId();
        assertTrue(renteeThumbnailRepository.findById(bookThumbnailId).isPresent());
    }

    @DisplayName("updateRenteeById 정상동작 테스트")
    @Test
    void test_updateRenteeById() {
        // given
        Rentee book = renteeService.create(book1SaveDto);
        long bookId = book.getId();
        long bookThumbnailId = book.getThumbnail().getId();

        // when
        renteeService.updateByRenteeId(bookId, book2SaveDto);

        // then
        Rentee updatedBook = renteeService.getRenteeByRenteeId(bookId);
        assertEquals(book2SaveDto.getType(), updatedBook.getType());
        assertEquals(book2SaveDto.getName(), updatedBook.getName());
        assertEquals(book2SaveDto.getBookArea(), updatedBook.getBookArea());
        assertEquals(book2SaveDto.getBookAuthorName(), updatedBook.getBookAuthorName());
        assertEquals(book2SaveDto.getBookPublisherName(), updatedBook.getBookPublisherName());
        assertEquals(EpochTime.toLocalDate(book2SaveDto.getBookPublishedDateEpochSecond()), updatedBook.getBookPublishedDate());
        assertEquals(book2SaveDto.getNote(), updatedBook.getNote());
        assertEquals(RentState.RENTABLE, updatedBook.getRentState());
        assertEquals(0, updatedBook.getRentCount());

        long updatedBookThumbnailId = updatedBook.getThumbnail().getId();
        assertNotEquals(bookThumbnailId, updatedBookThumbnailId);
    }

    @DisplayName("deleteRenteeById 정상동작 테스트")
    @Test
    void test_deleteRenteeById() {
        // given
        Rentee book = renteeService.create(book1SaveDto);
        long bookId = book.getId();
        long thumbnailId = book.getThumbnail().getId();

        // when
        renteeService.deleteByRenteeId(bookId);

        // then
        assertEquals(Optional.empty(), renteeRepository.findById(bookId));
        assertEquals(Optional.empty(), renteeThumbnailRepository.findById(thumbnailId));
    }
}
