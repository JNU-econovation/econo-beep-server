//package com.econo.econobeepserver.service;
//
//import com.econo.econobeepserver.domain.Rentee.Rentee;
//import com.econo.econobeepserver.domain.Rentee.RenteeThumbnail;
//import com.econo.econobeepserver.domain.Rentee.RenteeRepository;
//import com.econo.econobeepserver.domain.Rentee.RenteeType;
//import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
//import com.econo.econobeepserver.dto.Rentee.RenteeInfoDto;
//import com.econo.econobeepserver.dto.Rentee.RenteeManagementInfoDto;
//import com.econo.econobeepserver.exception.ExceptionMessage;
//import com.econo.econobeepserver.exception.NotFoundRenteeException;
//import com.econo.econobeepserver.service.Rentee.RenteeService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doReturn;
//
//@ExtendWith(MockitoExtension.class)
//class RenteeServiceRTest {
//
//    @InjectMocks
//    private RenteeService renteeService;
//
//    @Mock
//    private RenteeRepository renteeRepository;
//
//
//
//    private Optional<Rentee> successfulResponseOfFindById() {
//        return Optional.of(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage.jpg")
//                                        .build()
//                        )
//                        .title("testRentee")
//                        .type(RenteeType.WEB)
//                        .authorName("testAuthor")
//                        .publisherName("testPublisher")
//                        .publishedDate(LocalDate.of(1999, 10, 18))
//                        .note("test")
//                        .build()
//        );
//    }
//
//    private Optional<Rentee> failedResponseOfFindById() {
//        return Optional.empty();
//    }
//
//    @DisplayName("getRenteeInfoDtoById 작동 테스트")
//    @Test
//    void test_getRenteeInfoDtoById() {
//        // given
//        Optional<Rentee> successfulResponse = successfulResponseOfFindById();
//        doReturn(successfulResponse)
//                .when(renteeRepository)
//                .findById(1L);
//
//        RenteeInfoDto wantedResult = new RenteeInfoDto(successfulResponse.get());
//
//
//        // when & then
//        assertDoesNotThrow(() -> {
//            RenteeInfoDto result = renteeService.getRenteeInfoDtoById(1L);
//            assertTrue(new ReflectionEquals(wantedResult).matches(result));
//        });
//    }
//
//    @DisplayName("getRenteeInfoDtoById 오류 반응 테스트 (not exist id params)")
//    @Test
//    void test_getRenteeInfoDtoById_notExistIdParams() {
//        // given
//        Optional<Rentee> failedResponse = failedResponseOfFindById();
//        doReturn(failedResponse)
//                .when(renteeRepository)
//                .findById(-1L);
//
//
//        // when & then
//        Exception exception = assertThrows(NotFoundRenteeException.class, () -> {
//            renteeService.getRenteeInfoDtoById(-1L);
//        });
//        assertEquals(ExceptionMessage.NOT_FOUND_RENTEE_EXCEPTION.getMessage(), exception.getMessage());
//    }
//
//
//    private List<Rentee> successfulResponseOfGetRenteeWithPaging() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage.jpg")
//                                        .build()
//                        )
//                        .title("testRentee1")
//                        .type(RenteeType.WEB)
//                        .authorName("testAuthor1")
//                        .publisherName("testPublisher1")
//                        .publishedDate(LocalDate.of(1999, 10, 18))
//                        .note("test")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage.jpg")
//                                        .build()
//                        )
//                        .title("testRentee2")
//                        .type(RenteeType.WEB)
//                        .authorName("testAuthor2")
//                        .publisherName("testPublisher2")
//                        .publishedDate(LocalDate.of(1999, 10, 18))
//                        .note("test")
//                        .build()
//        );
//    }
//
//    private List<Rentee> failedResponseOfGetRenteeWithPaging() {
//        return Collections.emptyList();
//    }
//
//    @DisplayName("getRenteeElementDtosWithPaging 작동 테스트")
//    @Test
//    void test_getRenteeElementDtosWithPaging() {
//        // given
//        List<Rentee> successfulResponse = successfulResponseOfGetRenteeWithPaging();
//        doReturn(successfulResponse)
//                .when(renteeRepository)
//                .getRenteesWithPaging(2, null);
//
//        List<RenteeElementDto> wantedResults = successfulResponse.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.getRenteeElementDtosWithPaging(2, null);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("getRenteeElementDtosWithPaging 엣지케이스 테스트 (exceeded lastId params")
//    @Test
//    void test_getRenteeElementDtosWithPaging_exceededLastIdParams() {
//        // given
//        List<Rentee> failedResponse = failedResponseOfGetRenteeWithPaging();
//        doReturn(failedResponse)
//                .when(renteeRepository)
//                .getRenteesWithPaging(2, 10L);
//
//        List<RenteeElementDto> wantedResults = failedResponse.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.getRenteeElementDtosWithPaging(2, 10L);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//
//    @DisplayName("getRenteeElementDtosByRenteeTypeEqualWithPaging 작동 테스트")
//    @Test
//    void test_getRenteeElementDtosByRenteeTypeEqualWithPaging() {
//        // given
//        List<Rentee> successfulResponse = successfulResponseOfGetRenteeWithPaging();
//        doReturn(successfulResponse)
//                .when(renteeRepository)
//                .getRenteesByTypeEqualBySortWithPaging(RenteeType.WEB, 2, null);
//
//        List<RenteeElementDto> wantedResults = successfulResponse.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.getRenteeElementDtosByRenteeTypeEqualWithPaging(RenteeType.WEB, 2, null);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("getRenteeElementDtosByRenteeTypeEqualWithPaging 엣지케이스 테스트 (exceeded lastId params")
//    @Test
//    void test_getRenteeElementDtosByRenteeTypeEqualWithPaging_exceededLastIdParams() {
//        // given
//        List<Rentee> failedResponse = failedResponseOfGetRenteeWithPaging();
//        doReturn(failedResponse)
//                .when(renteeRepository)
//                .getRenteesByTypeEqualBySortWithPaging(RenteeType.WEB, 2, 10L);
//
//        List<RenteeElementDto> wantedResults = failedResponse.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.getRenteeElementDtosByRenteeTypeEqualWithPaging(RenteeType.WEB, 2, 10L);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//
//    private List<Rentee> successfulResponseOfSearchRenteeByKeyword() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage.jpg")
//                                        .build()
//                        )
//                        .title("testRentee1")
//                        .type(RenteeType.WEB)
//                        .authorName("testAuthor1")
//                        .publisherName("testPublisher1")
//                        .publishedDate(LocalDate.of(1999, 10, 18))
//                        .note("test")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage.jpg")
//                                        .build()
//                        )
//                        .title("testRentee2")
//                        .type(RenteeType.WEB)
//                        .authorName("testAuthor2")
//                        .publisherName("testPublisher2")
//                        .publishedDate(LocalDate.of(1999, 10, 18))
//                        .note("test")
//                        .build()
//        );
//    }
//
//    private List<Rentee> failedResponseOfSearchRenteeByKeyword() {
//        return Collections.emptyList();
//    }
//
//    @DisplayName("searchRenteeElementDtosByKeyword 작동 테스트")
//    @Test
//    void test_searchRenteeElementDtosByKeyword() {
//        // given
//        List<Rentee> successfulResponse = successfulResponseOfSearchRenteeByKeyword();
//        doReturn(successfulResponse)
//                .when(renteeRepository)
//                .searchRentee("test");
//
//        List<RenteeElementDto> wantedResults = successfulResponse.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByKeyword("test", );
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeElementDtosByKeyword 엣지케이스 테스트 (not exist keyword")
//    @Test
//    void test_searchRenteeElementDtosByKeyword_notExistKeyword() {
//        // given
//        List<Rentee> failedResponse = failedResponseOfSearchRenteeByKeyword();
//        doReturn(failedResponse)
//                .when(renteeRepository)
//                .searchRentee("real");
//
//        List<RenteeElementDto> wantedResults = failedResponse.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByKeyword("real");
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//
//    private List<Rentee> successfulResponseOfGetRenteeByIdDescWithPaging() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage.jpg")
//                                        .build()
//                        )
//                        .title("testRentee2")
//                        .type(RenteeType.WEB)
//                        .authorName("testAuthor2")
//                        .publisherName("testPublisher2")
//                        .publishedDate(LocalDate.of(1999, 10, 18))
//                        .note("test")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage.jpg")
//                                        .build()
//                        )
//                        .title("testRentee1")
//                        .type(RenteeType.WEB)
//                        .authorName("testAuthor1")
//                        .publisherName("testPublisher1")
//                        .publishedDate(LocalDate.of(1999, 10, 18))
//                        .note("test")
//                        .build()
//        );
//    }
//
//    private List<Rentee> failedResponseOfGetRenteeByIdDescWithPaging() {
//        return Collections.emptyList();
//    }
//
//    @DisplayName("getRenteeManagementInfoDtosByIdDescWithPaging 작동 테스트")
//    @Test
//    void test_getRenteeManagementInfoDtosByIdDescWithPaging() {
//        // given
//        List<Rentee> successfulResponse = successfulResponseOfGetRenteeByIdDescWithPaging();
//        doReturn(successfulResponse)
//                .when(renteeRepository)
//                .getRenteesWithPaging(2, null);
//
//        List<RenteeManagementInfoDto> wantedResults = successfulResponse.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.getRenteeManagementInfoDtosByIdDescWithPaging(2, null);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("getRenteeManagementInfoDtosByIdDescWithPaging 엣지케이스 테스트 (exceeded lastId params)")
//    @Test
//    void test_getRenteeManagementInfoDtosByIdDescWithPaging_exceededLastIdParams() {
//        // given
//        List<Rentee> failedResponse = failedResponseOfGetRenteeByIdDescWithPaging();
//        doReturn(failedResponse)
//                .when(renteeRepository)
//                .getRenteesWithPaging(2, 10L);
//
//        List<RenteeManagementInfoDto> wantedResults = failedResponse.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.getRenteeManagementInfoDtosByIdDescWithPaging(2, 10L);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosByKeyword 작동 테스트")
//    @Test
//    void test_searchRenteeManagementInfoDtosByKeyword() {
//        // given
//        List<Rentee> successfulResponse = successfulResponseOfSearchRenteeByKeyword();
//        doReturn(successfulResponse)
//                .when(renteeRepository)
//                .searchRentee("test");
//
//        List<RenteeManagementInfoDto> wantedResults = successfulResponse.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtos("test");
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosByKeyword 엣지케이스 테스트 (not exist keyword)")
//    @Test
//    void test_searchRenteeManagementInfoDtosByKeyword_notExistKeyword() {
//        // given
//        List<Rentee> failedResponse = failedResponseOfSearchRenteeByKeyword();
//        doReturn(failedResponse)
//                .when(renteeRepository)
//                .searchRentee("real");
//
//        List<RenteeManagementInfoDto> wantedResults = failedResponse.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtos("real");
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//}
