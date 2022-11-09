//package com.econo.econobeepserver.service;
//
//import com.econo.econobeepserver.domain.Rentee.*;
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
//class RenteeServiceUnitTest {
//
//    @InjectMocks
//    private RenteeService renteeService;
//
//    @Mock
//    private RenteeRepository renteeRepository;
//
//
//    private Optional<Rentee> findByIdResponse() {
//        return Optional.of(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage.jpg")
//                                        .build()
//                        )
//                        .name("testRentee")
//                        .type(RenteeType.WEB)
//                        .bookAuthorName("testAuthor")
//                        .bookPublisherName("testPublisher")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("test")
//                        .build()
//        );
//    }
//
//    @DisplayName("getRenteeInfoDtoById 작동 테스트")
//    @Test
//    void test_getRenteeInfoDtoById() {
//        // given
//        Optional<Rentee> response = findByIdResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .findById(1L);
//
//        RenteeInfoDto wantedResult = new RenteeInfoDto(response.get());
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
//        doReturn(Optional.empty())
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
//    //
//    //
//
//
//    private List<Rentee> searchRenteeWithPagingResponse() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeWithPaging1")
//                        .type(RenteeType.WEB)
//                        .bookAuthorName("testAuthor1")
//                        .bookPublisherName("testPublisher1")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeWithPaging1")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeWithPaging2")
//                        .type(RenteeType.WEB)
//                        .bookAuthorName("testAuthor2")
//                        .bookPublisherName("testPublisher2")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeWithPaging2")
//                        .build()
//        );
//    }
//
//    @DisplayName("searchRenteeElementDtosByKeyword 작동 테스트")
//    @Test
//    void test_searchRenteeElementDtosByKeyword() {
//        // given
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = null;
//
//        List<Rentee> response = searchRenteeWithPagingResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .searchRenteeWithPaging(keyword, pageSize, lastId);
//
//        List<RenteeElementDto> wantedResults = response.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByKeyword(keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeElementDtosByKeyword 작동 테스트 (last lastId params")
//    @Test
//    void test_searchRenteeElementDtosByKeyword_lastLastIdParams() {
//        // given
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = 2L;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeWithPaging(keyword, pageSize, lastId);
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByKeyword(keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    @DisplayName("searchRenteeElementDtosByKeyword 작동 테스트 (not exist keyword params")
//    @Test
//    void test_searchRenteeElementDtosByKeyword_notExistKeywordParams() {
//        // given
//        final String keyword = "notExist";
//        final int pageSize = 2;
//        final Long lastId = null;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeWithPaging(keyword, pageSize, lastId);
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByKeyword(keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//
//    //
//    //
//
//
//    private List<Rentee> searchRenteeByRenteeTypeEqualWithPagingResponse() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeEqualWithPaging1")
//                        .type(RenteeType.WEB)
//                        .bookAuthorName("testAuthor1")
//                        .bookPublisherName("testPublisher1")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeEqualWithPaging1")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeEqualWithPaging2")
//                        .type(RenteeType.WEB)
//                        .bookAuthorName("testAuthor2")
//                        .bookPublisherName("testPublisher2")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeEqualWithPaging2")
//                        .build()
//        );
//    }
//
//    @DisplayName("searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging 작동 테스트")
//    @Test
//    void test_searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging() {
//        // given
//        final RenteeType renteeType = RenteeType.WEB;
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = null;
//
//        List<Rentee> response = searchRenteeByRenteeTypeEqualWithPagingResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//        List<RenteeElementDto> wantedResults = response.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging(renteeType, keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging 작동 테스트 (last lastId params")
//    @Test
//    void test_searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging_lastLastIdParams() {
//        // given
//        final RenteeType renteeType = RenteeType.WEB;
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = 2L;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging(renteeType, keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    @DisplayName("searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging 작동 테스트 (not exist keyword params")
//    @Test
//    void test_searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging_notExistKeywordParams() {
//        // given
//        final RenteeType renteeType = RenteeType.WEB;
//        final String keyword = "notExist";
//        final int pageSize = 2;
//        final Long lastId = null;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByRenteeTypeEqualByKeywordWithPaging(renteeType, keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    //
//    //
//
//    private List<Rentee> searchRenteeByRenteeTypeNotEqualWithPagingResponse() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeNotEqualWithPaging1")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor1")
//                        .bookPublisherName("testPublisher1")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeNotEqualWithPaging1")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeNotEqualWithPaging2")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor2")
//                        .bookPublisherName("testPublisher2")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeNotEqualWithPaging2")
//                        .build()
//        );
//    }
//
//    @DisplayName("searchRenteeElementDtosByRenteeTypeNotEqualWithPaging 작동 테스트")
//    @Test
//    void test_searchRenteeElementDtosByRenteeTypeNotEqualWithPaging() {
//        // given
//        final RenteeType renteeType = RenteeType.WEB;
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = null;
//
//        List<Rentee> response = searchRenteeByRenteeTypeNotEqualWithPagingResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeNotEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//        List<RenteeElementDto> wantedResults = response.stream().map(RenteeElementDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByRenteeTypeNotEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeElementDtosByRenteeTypeNotEqualWithPaging 작동 테스트 (last lastId params")
//    @Test
//    void test_searchRenteeElementDtosByRenteeTypeNotEqualWithPaging_lastLastIdParams() {
//        // given
//        final RenteeType renteeType = RenteeType.WEB;
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = 2L;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeNotEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByRenteeTypeNotEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    @DisplayName("searchRenteeElementDtosByRenteeTypeNotEqualWithPaging 작동 테스트 (not exist keyword params")
//    @Test
//    void test_searchRenteeElementDtosByRenteeTypeNotEqualWithPaging_notExistKeywordParams() {
//        // given
//        final RenteeType renteeType = RenteeType.WEB;
//        final String keyword = "notExist";
//        final int pageSize = 2;
//        final Long lastId = null;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeNotEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//
//        // when
//        List<RenteeElementDto> results = renteeService.searchRenteeElementDtosByRenteeTypeNotEqualWithPaging(renteeType, keyword, pageSize, lastId);
//
//        // then
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    //
//    //
//
//    private List<Rentee> searchRenteeByRenteeTypeNotEqualByIdSortPagingResponse() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeNotEqualByIdSortPaging1")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor1")
//                        .bookPublisherName("testPublisher1")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeNotEqualByIdSortPaging1")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeNotEqualByIdSortPaging2")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor2")
//                        .bookPublisherName("testPublisher2")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeNotEqualByIdSortPaging2")
//                        .build()
//        );
//    }
//
//    private List<Rentee> searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPagingResponse() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPaging1")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor1")
//                        .bookPublisherName("testPublisher1")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPaging1")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPaging2")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor2")
//                        .bookPublisherName("testPublisher2")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPaging2")
//                        .build()
//        );
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosFromBookWithPaging 작동 테스트 (id sort)")
//    @Test
//    void test_searchRenteeManagementInfoDtosFromBookWithPaging_idSort() {
//        // given
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = null;
//        final Long offset = 0L;
//        final Boolean isIdAsc = true;
//        final Boolean isIdDesc = null;
//        final Boolean isRecentRentDesc = null;
//
//        List<Rentee> response = searchRenteeByRenteeTypeNotEqualByIdSortPagingResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeNotEqualByIdSortPaging(RenteeType.EQUIPMENT, keyword, pageSize, lastId, isIdAsc, isIdDesc);
//
//        List<RenteeManagementInfoDto> wantedResults = response.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtosFromBookWithPaging(keyword, pageSize, lastId, offset, isIdAsc, isIdDesc, isRecentRentDesc);
//
//        // then
//        assertNotNull(results);
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosFromBookWithPaging 작동 테스트 (except for id sort)")
//    @Test
//    void test_searchRenteeManagementInfoDtosFromBookWithPaging_exceptForIdSort() {
//        // given
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = null;
//        final Long offset = 0L;
//        final Boolean isIdAsc = null;
//        final Boolean isIdDesc = null;
//        final Boolean isRecentRentDesc = true;
//
//        List<Rentee> response = searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPagingResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeNotEqualByRecentRentDescWithPaging(RenteeType.EQUIPMENT, keyword, pageSize, offset);
//
//        List<RenteeManagementInfoDto> wantedResults = response.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtosFromBookWithPaging(keyword, pageSize, lastId, offset, isIdAsc, isIdDesc, isRecentRentDesc);
//
//        // then
//        assertNotNull(results);
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosFromBookWithPaging 작동 테스트 (id sort, last lastId params")
//    @Test
//    void test_searchRenteeManagementInfoDtosFromBookWithPaging_lastLastIdParams() {
//        // given
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = 2L;
//        final Long offset = 0L;
//        final Boolean isIdAsc = true;
//        final Boolean isIdDesc = null;
//        final Boolean isRecentRentDesc = null;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeNotEqualByIdSortPaging(RenteeType.EQUIPMENT, keyword, pageSize, lastId, isIdAsc, isIdDesc);
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtosFromBookWithPaging(keyword, pageSize, lastId, offset, isIdAsc, isIdDesc, isRecentRentDesc);
//
//        // then
//        assertNotNull(results);
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosFromBookWithPaging 작동 테스트 (id sort, not exist keyword params")
//    @Test
//    void test_searchRenteeManagementInfoDtosFromBookWithPaging_notExistKeywordParams() {
//        // given
//        final String keyword = "notExist";
//        final int pageSize = 2;
//        final Long lastId = null;
//        final Long offset = 0L;
//        final Boolean isIdAsc = true;
//        final Boolean isIdDesc = null;
//        final Boolean isRecentRentDesc = null;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeNotEqualByIdSortPaging(RenteeType.EQUIPMENT, keyword, pageSize, lastId, isIdAsc, isIdDesc);
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtosFromBookWithPaging(keyword, pageSize, lastId, offset, isIdAsc, isIdDesc, isRecentRentDesc);
//
//        // then
//        assertNotNull(results);
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    //
//    //
//
//    private List<Rentee> searchRenteeByRenteeTypeEqualByIdSortWithPagingResponse() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeEqualByIdSortWithPaging1")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor1")
//                        .bookPublisherName("testPublisher1")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeEqualByIdSortWithPaging1")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeEqualByIdSortWithPaging2")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor2")
//                        .bookPublisherName("testPublisher2")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeEqualByIdSortWithPaging2")
//                        .build()
//        );
//    }
//
//    private List<Rentee> searchRenteeByRenteeTypeEqualByRecentRentDescWithPagingResponse() {
//        return Arrays.asList(
//                Rentee.builder()
//                        .id(1L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeEqualByRecentRentDescWithPaging1")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor1")
//                        .bookPublisherName("testPublisher1")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeEqualByRecentRentDescWithPaging1")
//                        .build(),
//
//                Rentee.builder()
//                        .id(2L)
//                        .thumbnail(
//                                RenteeThumbnail.builder()
//                                        .filePath("images/testImage1.jpg")
//                                        .build()
//                        )
//                        .name("searchRenteeByRenteeTypeEqualByRecentRentDescWithPaging2")
//                        .type(RenteeType.APP)
//                        .bookAuthorName("testAuthor2")
//                        .bookPublisherName("testPublisher2")
//                        .bookPublishedDate(LocalDate.of(1999, 10, 18))
//                        .note("searchRenteeByRenteeTypeEqualByRecentRentDescWithPaging2")
//                        .build()
//        );
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosFromDeviceWithPaging 작동 테스트 (id sort)")
//    @Test
//    void test_searchRenteeManagementInfoDtosFromDeviceWithPaging_idSort() {
//        // given
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = null;
//        final Long offset = 0L;
//        final Boolean isIdAsc = true;
//        final Boolean isIdDesc = null;
//        final Boolean isRecentRentDesc = null;
//
//        List<Rentee> response = searchRenteeByRenteeTypeEqualByIdSortWithPagingResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeEqualByIdSortWithPaging(RenteeType.EQUIPMENT, keyword, pageSize, lastId, isIdAsc, isIdDesc);
//
//        List<RenteeManagementInfoDto> wantedResults = response.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtosFromDeviceWithPaging(keyword, pageSize, lastId, offset, isIdAsc, isIdDesc, isRecentRentDesc);
//
//        // then
//        assertNotNull(results);
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosFromDeviceWithPaging 작동 테스트 (except for id sort)")
//    @Test
//    void test_searchRenteeManagementInfoDtosFromDeviceWithPaging_exceptForIdSort() {
//        // given
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = null;
//        final Long offset = 0L;
//        final Boolean isIdAsc = null;
//        final Boolean isIdDesc = null;
//        final Boolean isRecentRentDesc = true;
//
//        List<Rentee> response = searchRenteeByRenteeTypeEqualByRecentRentDescWithPagingResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeEqualByRecentRentDescWithPaging(RenteeType.EQUIPMENT, keyword, pageSize, offset);
//
//        List<RenteeManagementInfoDto> wantedResults = response.stream().map(RenteeManagementInfoDto::new).collect(Collectors.toList());
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtosFromDeviceWithPaging(keyword, pageSize, lastId, offset, isIdAsc, isIdDesc, isRecentRentDesc);
//
//        // then
//        assertNotNull(results);
//        assertIterableEquals(wantedResults, results);
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosFromDeviceWithPaging 작동 테스트 (id sort, last lastId params")
//    @Test
//    void test_searchRenteeManagementInfoDtosFromDeviceWithPaging_lastLastIdParams() {
//        // given
//        final String keyword = "";
//        final int pageSize = 2;
//        final Long lastId = 2L;
//        final Long offset = 0L;
//        final Boolean isIdAsc = true;
//        final Boolean isIdDesc = null;
//        final Boolean isRecentRentDesc = null;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeEqualByIdSortWithPaging(RenteeType.EQUIPMENT, keyword, pageSize, lastId, isIdAsc, isIdDesc);
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtosFromDeviceWithPaging(keyword, pageSize, lastId, offset, isIdAsc, isIdDesc, isRecentRentDesc);
//
//        // then
//        assertNotNull(results);
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    @DisplayName("searchRenteeManagementInfoDtosFromDeviceWithPaging 작동 테스트 (id sort, not exist keyword params")
//    @Test
//    void test_searchRenteeManagementInfoDtosFromDeviceWithPaging_notExistKeywordParams() {
//        // given
//        final String keyword = "notExist";
//        final int pageSize = 2;
//        final Long lastId = null;
//        final Long offset = 0L;
//        final Boolean isIdAsc = true;
//        final Boolean isIdDesc = null;
//        final Boolean isRecentRentDesc = null;
//
//        doReturn(Collections.emptyList())
//                .when(renteeRepository)
//                .searchRenteeByRenteeTypeEqualByIdSortWithPaging(RenteeType.EQUIPMENT, keyword, pageSize, lastId, isIdAsc, isIdDesc);
//
//
//        // when
//        List<RenteeManagementInfoDto> results = renteeService.searchRenteeManagementInfoDtosFromDeviceWithPaging(keyword, pageSize, lastId, offset, isIdAsc, isIdDesc, isRecentRentDesc);
//
//        // then
//        assertNotNull(results);
//        assertIterableEquals(Collections.emptyList(), results);
//    }
//
//    //
//    //
//
//    @DisplayName("getThumbnailFilePathByRenteeId 작동 테스트")
//    @Test
//    void test_getThumbnailFilePathByRenteeId() {
//        // given
//        Optional<Rentee> response = findByIdResponse();
//        doReturn(response)
//                .when(renteeRepository)
//                .findById(1L);
//
//        String wantedResult = response.get().getThumbnail().getFilePath();
//
//
//        // when & then
//        assertDoesNotThrow(() -> {
//            String result = renteeService.getThumbnailFilePathByRenteeId(1L);
//            assertEquals(wantedResult, result);
//        });
//    }
//
//    @DisplayName("getThumbnailFilePathByRenteeId 오류 반응 테스트 (not exist id params)")
//    @Test
//    void test_getThumbnailFilePathByRenteeId_notExistIdParams() {
//        // given
//        doReturn(Optional.empty())
//                .when(renteeRepository)
//                .findById(-1L);
//
//
//        // when & then
//        Exception exception = assertThrows(NotFoundRenteeException.class, () -> {
//            renteeService.getThumbnailFilePathByRenteeId(-1L);
//        });
//        assertEquals(ExceptionMessage.NOT_FOUND_RENTEE_EXCEPTION.getMessage(), exception.getMessage());
//    }
//}
