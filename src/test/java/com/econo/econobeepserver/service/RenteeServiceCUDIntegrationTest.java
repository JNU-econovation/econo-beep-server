package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Rentee.*;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.econo.econobeepserver.service.Rentee.RenteeService;
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
class RenteeServiceCUDIntegrationTest {

    @Autowired
    private RenteeService renteeService;

    @Autowired
    private RenteeRepository renteeRepository;

    @Autowired
    private RenteeThumbnailRepository renteeThumbnailRepository;


    MockMultipartFile thumbnail;
    MockMultipartFile updateThumbnail;
    RenteeSaveDto book1SaveDto;
    RenteeSaveDto book2SaveDto;

    public RenteeServiceCUDIntegrationTest() throws IOException {
        thumbnail = new MockMultipartFile(TEST_JPG_NAME, TEST_JPG_NAME, "image/jpg", new FileInputStream(TEST_JPG_PATH));
        updateThumbnail = new MockMultipartFile(UPDATE_TEST_JPG_NAME, UPDATE_TEST_JPG_NAME, "image/jpg", new FileInputStream(UPDATE_TEST_JPG_PATH));
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
        renteeService.createRentee(book1SaveDto);

        // then
        Rentee book = renteeService.getRenteeByName(book1SaveDto.getName());
        assertEquals(book1SaveDto, book);
//        assertTrue(book1SaveDto.equals(book));
        assertNotNull(book.getThumbnail());
    }

    @DisplayName("updateRenteeById 정상동작 테스트")
    @Test
    void test_updateRenteeById() {
        // given
        Rentee book = renteeService.createRentee(book1SaveDto);
        long bookId = book.getId();

        // when
        renteeService.updateRenteeById(bookId, book2SaveDto);

        // then
        Rentee updatedBook = renteeService.getRenteeById(bookId);
        assertEquals(book2SaveDto, updatedBook);
    }

    @DisplayName("deleteRenteeById 정상동작 테스트")
    @Test
    void test_deleteRenteeById() {
        // given
        Rentee book = renteeService.createRentee(book1SaveDto);
        long bookId = book.getId();
        long thumbnailId = book.getThumbnail().getId();

        // when
        renteeService.deleteRenteeById(bookId);

        // then
        assertEquals(Optional.empty(), renteeRepository.findById(bookId));
        assertEquals(Optional.empty(), renteeThumbnailRepository.findById(thumbnailId));
    }
}
