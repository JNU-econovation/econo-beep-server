package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RenteeThumbnail;
import com.econo.econobeepserver.domain.Rentee.RenteeThumbnailRepository;
import com.econo.econobeepserver.domain.Rentee.RenteeRepository;
import com.econo.econobeepserver.domain.Rental.RentalRepository;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import com.econo.econobeepserver.domain.User.UserApi;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.Rentee.RenteeRentalService;
import com.econo.econobeepserver.service.Rentee.RenteeService;
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
class RenteeServiceSpringTest {

    @Autowired
    private RenteeService renteeService;

    @Autowired
    private RenteeRepository renteeRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RenteeThumbnailRepository thumbnailRepository;

    @Autowired
    @InjectMocks
    private RenteeRentalService renteeRentalService;
    @MockBean
    private UserApi userApi;


    private final Long NOT_FOUND_BOOK_ID = 100L;
    private final String SAMPLE_PINCODE = "1234";

    private static final String ABSOLUTE_PATH = new File("").getAbsolutePath();
    private static final String TEST_IMAGES_PATH = ABSOLUTE_PATH + "/src/test/java/com/econo/econobeepserver/images/";
    private static final String REAL_IMAGES_PATH = ABSOLUTE_PATH + "/images/rentee/thumbnail";
    private static final String THUMBNAIL_NAME = "testThumbnail.jpg";
    private static final String UPDATED_THUMBNAIL_NAME = "updateTestThumbnail.jpg";
    private static final String THUMBNAIL_PATH = TEST_IMAGES_PATH + THUMBNAIL_NAME;
    private static final String UPDATED_THUMBNAIL_PATH = TEST_IMAGES_PATH + UPDATED_THUMBNAIL_NAME;


    @AfterEach
    void reset() {
        thumbnailRepository.deleteAll();
        rentalRepository.deleteAll();
        renteeRepository.deleteAll();

        File thumbnailPath = new File(REAL_IMAGES_PATH);
        if (thumbnailPath.exists()) {
            for (File thumbnail : thumbnailPath.listFiles()) {
                thumbnail.delete();
            }
        }
    }

    private RenteeSaveDto newRenteeSaveDto() {
        try {
            System.out.println(THUMBNAIL_PATH);
            return RenteeSaveDto.builder()
                    .title("testRentee")
                    .type(RenteeType.APP)
                    .authorName("testAuthor")
                    .publisherName("testPublisher")
                    .publishedDateEpochSecond(toEpochSecond(LocalDate.of(1999, 10, 18)))
                    .thumbnail(new MockMultipartFile(THUMBNAIL_NAME, THUMBNAIL_NAME, "image/jpg", new FileInputStream(THUMBNAIL_PATH)))
                    .note("test")
                    .build();
        } catch (IOException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    private RenteeSaveDto updatedRenteeSaveDto() {
        try {
            return RenteeSaveDto.builder()
                    .title("testRenteeUpdate")
                    .type(RenteeType.APP)
                    .authorName("testAuthorUpdate")
                    .publisherName("testPublisherUpdate")
                    .publishedDateEpochSecond(toEpochSecond(LocalDate.of(2022, 10, 18)))
                    .thumbnail(new MockMultipartFile("testThumbnailUpdate.jpg", "testThumbnailUpdate.jpg", "image/jpg", new FileInputStream(UPDATED_THUMBNAIL_PATH)))
                    .note("testUpdate")
                    .build();
        } catch (IOException e) {
            return null;
        }
    }

    @DisplayName("createRentee 작동 테스트")
    @Test
    void test_createRentee() {
        // given
        RenteeSaveDto newRenteeSaveDto = newRenteeSaveDto();

        // when
        long renteeId = renteeService.createRentee(newRenteeSaveDto);

        // then
        assertEquals(1, renteeRepository.count());

        Rentee savedRentee = renteeRepository.findById(renteeId).get();
        assertEquals(newRenteeSaveDto.getTitle(), savedRentee.getTitle());
        assertEquals(newRenteeSaveDto.getType(), savedRentee.getType());
        assertEquals(newRenteeSaveDto.getAuthorName(), savedRentee.getAuthorName());
        assertEquals(newRenteeSaveDto.getPublisherName(), savedRentee.getPublisherName());
        assertEquals(newRenteeSaveDto.getPublishedDateEpochSecond(), toEpochSecond(savedRentee.getPublishedDate()));
        assertNotNull(savedRentee.getThumbnail());
        assertEquals(newRenteeSaveDto.getNote(), savedRentee.getNote());

        assertEquals(1L, thumbnailRepository.count());
        RenteeThumbnail thumbnail = thumbnailRepository.findAll().get(0);
        assertEquals(renteeId, thumbnail.getRentee().getId());
    }


    @DisplayName("updateRenteeById 작동 테스트")
    @Test
    void test_updateRenteeById() {
        // given
        RenteeSaveDto newRenteeSaveDto = newRenteeSaveDto();
        long renteeId = renteeService.createRentee(newRenteeSaveDto);
        String oldRenteeCoverFilePath = renteeService.getRenteeById(renteeId).getThumbnail().getFilePath();
        RenteeSaveDto updatedRenteeSaveDto = updatedRenteeSaveDto();

        // when
        renteeService.updateRenteeById(renteeId, updatedRenteeSaveDto());

        // then
        Rentee savedRentee = renteeRepository.findById(renteeId).get();
        assertEquals(updatedRenteeSaveDto.getTitle(), savedRentee.getTitle());
        assertEquals(updatedRenteeSaveDto.getType(), savedRentee.getType());
        assertEquals(updatedRenteeSaveDto.getAuthorName(), savedRentee.getAuthorName());
        assertEquals(updatedRenteeSaveDto.getPublisherName(), savedRentee.getPublisherName());
        assertEquals(updatedRenteeSaveDto.getPublishedDateEpochSecond(), toEpochSecond(savedRentee.getPublishedDate()));
        assertNotEquals(oldRenteeCoverFilePath, savedRentee.getThumbnail().getFilePath());
        assertEquals(updatedRenteeSaveDto.getNote(), savedRentee.getNote());

        assertEquals(1L, thumbnailRepository.count());
        RenteeThumbnail thumbnail = thumbnailRepository.findAll().get(0);
        assertEquals(renteeId, thumbnail.getRentee().getId());
    }


    @DisplayName("updateRenteeById 오류 대응 테스트 (not exist id params)")
    @Test
    void test_updateRenteeById_notExistIdParams() {
        // given
        renteeService.createRentee(newRenteeSaveDto());


        // when & then
        assertThrows(NotFoundRenteeException.class, () -> {
            renteeService.updateRenteeById(NOT_FOUND_BOOK_ID, updatedRenteeSaveDto());
        });
    }


    private UserInfoDto sampleUser() {
        return UserInfoDto.builder()
                .id(1L)
                .userName("tester")
                .pinCode("1234")
                .build();
    }

    @DisplayName("deleteRenteeById 작동 테스트")
    @Test
    @Transactional
    void test_deleteRenteeById() {
        // given
        long renteeId = renteeService.createRentee(newRenteeSaveDto());
        String thumbnailPath = renteeService.getThumbnailFilePathByRenteeId(renteeId);
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);
        renteeRentalService.rentRenteeById(renteeId, SAMPLE_PINCODE);

        // when
        renteeService.deleteRenteeById(renteeId);
        ;

        // then
        assertTrue(renteeRepository.findById(renteeId).isEmpty());
        assertEquals(0L, thumbnailRepository.count());
        assertFalse(new File(thumbnailPath).exists());
        assertEquals(0L, rentalRepository.count());
    }

    @DisplayName("deleteRenteeById 오류 대응 테스트 (not exist id params)")
    @Test
    void test_deleteRenteeById_notExistIdParams() {
        // when & then
        assertThrows(NotFoundRenteeException.class, () -> {
            renteeService.deleteRenteeById(NOT_FOUND_BOOK_ID);
        });
    }
}
