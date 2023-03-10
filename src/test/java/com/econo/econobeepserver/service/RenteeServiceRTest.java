package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Rental.RentalRepository;
import com.econo.econobeepserver.domain.Rentee.BookArea;
import com.econo.econobeepserver.domain.Rentee.RenteeRepository;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import com.econo.econobeepserver.dto.Rentee.RenteeManagementInfoDto;
import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;
import com.econo.econobeepserver.service.Rentee.RentalService;
import com.econo.econobeepserver.service.Rentee.RenteeService;
import com.econo.econobeepserver.service.Rentee.RenteeSort;
import com.econo.econobeepserver.service.User.EconoIDPAdapter;
import com.econo.econobeepserver.service.User.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.econo.econobeepserver.service.ImageHandlerTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Nested
class RenteeServiceRTest {

    // Target
    @Autowired
    private RenteeService renteeService;

    // Essential Classes for R features of RenteeService /////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    private RenteeRepository renteeRepository;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserService userService;

    // Mock class for UserService
    @MockBean(name = "econoIDPAdapter")
    private EconoIDPAdapter econoIDPAdapter;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Rentees for test //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    RenteeSaveDto book1SaveDto;
    RenteeSaveDto book2SaveDto;
    RenteeSaveDto book3SaveDto;
    RenteeSaveDto device1SaveDto;
    RenteeSaveDto device2SaveDto;
    RenteeSaveDto device3SaveDto;
    UserIdpIdDto userIdpIdDto;
    UserIdpTokenDto userIdpTokenDto;
    public RenteeServiceRTest() throws IOException {
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
        book3SaveDto = RenteeSaveDto.builder()
                .thumbnail(thumbnail)
                .type(RenteeType.BOOK)
                .name("book3")
                .bookArea(BookArea.APP)
                .bookAuthorName("author3")
                .bookPublisherName("publisher3")
                .bookPublishedDateEpochSecond(1640998861L)
                .note("note3")
                .build();
        device1SaveDto = RenteeSaveDto.builder()
                .thumbnail(thumbnail)
                .type(RenteeType.DEVICE)
                .name("device1")
                .note("note1")
                .build();
        device2SaveDto = RenteeSaveDto.builder()
                .thumbnail(thumbnail)
                .type(RenteeType.DEVICE)
                .name("device2")
                .note("note2")
                .build();
        device3SaveDto = RenteeSaveDto.builder()
                .thumbnail(thumbnail)
                .type(RenteeType.DEVICE)
                .name("device3")
                .note("note3")
                .build();

        userIdpIdDto = UserIdpIdDto.builder()
                .id(1L)
                .year(21)
                .username("에코노")
                .email("econo@gmail.com")
                .build();
        userIdpTokenDto = UserIdpTokenDto.builder()
                .id(1L)
                .year(21)
                .username("에코노")
                .email("econo@gmail.com")
                .build();
    }

    @BeforeEach
    void initTestRentees() {
        renteeService.create(book1SaveDto);
        renteeService.create(book2SaveDto);
        renteeService.create(book3SaveDto);
        renteeService.create(device1SaveDto);
        renteeService.create(device2SaveDto);
        renteeService.create(device3SaveDto);

    }

    @AfterEach
    void clearTestRentees() {
        rentalRepository.deleteAll();
        renteeRepository.deleteAll();
    }

    @AfterAll
    static void removeSavedThumbnails() {
        File thumbnailStorage = new File(RENTEE_THUMBNAIL_FOLDER_PATH);
        for(File thumbnail : thumbnailStorage.listFiles()){
            thumbnail.delete();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






    @DisplayName("getRenteeElementDtosByRenteeNameWithPaging 정상동작 테스트")
    @Test
    void test_getRenteeElementDtosByRenteeNameWithPaging() {
        // when
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByRenteeNameContainingWithPaging("1", 0, 10);

        // then
        assertEquals(2, renteeElementDtos.size());
        assertEquals(book1SaveDto.getName(), renteeElementDtos.get(0).getName());
        assertEquals(device1SaveDto.getName(), renteeElementDtos.get(1).getName());
    }

    @DisplayName("getRenteeElementDtosByRenteeNameFromBookWithPaging 정상동작 테스트")
    @Test
    void test_getRenteeElementDtosByRenteeNameFromBookWithPaging() {
        // when
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByBookNameWithPaging("1", 0, 10);

        // then
        assertEquals(1, renteeElementDtos.size());
        assertEquals(book1SaveDto.getName(), renteeElementDtos.get(0).getName());
    }

    @DisplayName("getRenteeElementDtosByRenteeNameFromDeviceWithPaging 정상동작 테스트")
    @Test
    void test_getRenteeElementDtosByRenteeNameFromDeviceWithPaging() {
        // when
        List<RenteeElementDto> renteeElementDtos = renteeService.getRenteeElementDtosByDeviceNameWithPaging("1", 0, 10);

        // then
        assertEquals(1, renteeElementDtos.size());
        assertEquals(device1SaveDto.getName(), renteeElementDtos.get(0).getName());
    }

    //

    @Nested
    @DisplayName("searchRenteeManagementInfoDtosByRenteeNameFromBookWithSortAndPaging 정상동작 테스트")
    class SearchBookManagementInfoDtosByRenteeNameFromBookWithSortAndPaging_Test {

        @DisplayName("페이징 테스트")
        @Test
        void test_paging() {
            // when
            List<RenteeManagementInfoDto> bookManagementElementDtos = renteeService.getRenteeManagementInfoDtosByBookNameContainingWithSortAndPaging("book", RenteeSort.NONE, 0, 2);

            // then
            assertEquals(2, bookManagementElementDtos.size());
        }

        @DisplayName("오래전에 추가된순(CREATED_ASC) 정렬 테스트")
        @Test
        void test_createdAscSort() {
            // when
            List<RenteeManagementInfoDto> bookManagementElementDtos = renteeService.getRenteeManagementInfoDtosByBookNameContainingWithSortAndPaging("book", RenteeSort.CREATED_ASC, 0, 3);

            // then
            assertEquals(book1SaveDto.getName(), bookManagementElementDtos.get(0).getName());
            assertEquals(book2SaveDto.getName(), bookManagementElementDtos.get(1).getName());
            assertEquals(book3SaveDto.getName(), bookManagementElementDtos.get(2).getName());
        }

        @DisplayName("최근에 추가된순(CREATED_DESC) 정렬 테스트")
        @Test
        void test_createdDescSort() {
            // when
            List<RenteeManagementInfoDto> bookManagementElementDtos = renteeService.getRenteeManagementInfoDtosByBookNameContainingWithSortAndPaging("book", RenteeSort.CREATED_DESC, 0, 3);

            // then
            assertEquals(book3SaveDto.getName(), bookManagementElementDtos.get(0).getName());
            assertEquals(book2SaveDto.getName(), bookManagementElementDtos.get(1).getName());
            assertEquals(book1SaveDto.getName(), bookManagementElementDtos.get(2).getName());
        }

        @DisplayName("오래전에 대여된순(OUTDATED_RENTAL) 정렬 테스트")
        @Test
        void test_outdatedRentalSort() {
            // given
            given(econoIDPAdapter.getUserIdpTokenDtoByIdpToken("token")).willReturn(userIdpTokenDto);
            given(econoIDPAdapter.getUserIdpIdDtoByIdpId(userIdpTokenDto.getId())).willReturn(userIdpIdDto);
            long userId = userService.getUserByIdpToken("token").getId();

            long book1Id = renteeService.getRenteeByRenteeName(book1SaveDto.getName()).getId();
            long book2Id = renteeService.getRenteeByRenteeName(book2SaveDto.getName()).getId();
            long book3Id = renteeService.getRenteeByRenteeName(book3SaveDto.getName()).getId();

            rentalService.rentRenteeByRenteeIdAndUserId(book2Id, userId);
            rentalService.rentRenteeByRenteeIdAndUserId(book1Id, userId);
            rentalService.rentRenteeByRenteeIdAndUserId(book3Id, userId);


            // when
            List<RenteeManagementInfoDto> bookManagementElementDtos = renteeService.getRenteeManagementInfoDtosByBookNameContainingWithSortAndPaging("book", RenteeSort.OUTDATED_RENTAL, 0, 3);

            // then
            assertEquals(book2SaveDto.getName(), bookManagementElementDtos.get(0).getName());
            assertEquals(book1SaveDto.getName(), bookManagementElementDtos.get(1).getName());
            assertEquals(book3SaveDto.getName(), bookManagementElementDtos.get(2).getName());
        }

        @DisplayName("최근 대여된순(LATEST_RENTAL) 정렬 테스트")
        @Test
        void test_latestRentalSort() {
            // given
            given(econoIDPAdapter.getUserIdpTokenDtoByIdpToken("token")).willReturn(userIdpTokenDto);
            given(econoIDPAdapter.getUserIdpIdDtoByIdpId(userIdpTokenDto.getId())).willReturn(userIdpIdDto);
            long userId = userService.getUserByIdpToken("token").getId();

            long book1Id = renteeService.getRenteeByRenteeName(book1SaveDto.getName()).getId();
            long book2Id = renteeService.getRenteeByRenteeName(book2SaveDto.getName()).getId();
            long book3Id = renteeService.getRenteeByRenteeName(book3SaveDto.getName()).getId();

            rentalService.rentRenteeByRenteeIdAndUserId(book2Id, userId);
            rentalService.rentRenteeByRenteeIdAndUserId(book1Id, userId);
            rentalService.rentRenteeByRenteeIdAndUserId(book3Id, userId);

            // when
            List<RenteeManagementInfoDto> bookManagementElementDtos = renteeService.getRenteeManagementInfoDtosByBookNameContainingWithSortAndPaging("book", RenteeSort.LATEST_RENTAL, 0, 3);

            // then
            assertEquals(book3SaveDto.getName(), bookManagementElementDtos.get(0).getName());
            assertEquals(book1SaveDto.getName(), bookManagementElementDtos.get(1).getName());
            assertEquals(book2SaveDto.getName(), bookManagementElementDtos.get(2).getName());
        }
    }
}