package com.econo.econobeepserver.legacy.repository;

import com.econo.econobeepserver.TestConfig;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RenteeThumbnail;
import com.econo.econobeepserver.domain.Rentee.RenteeRepository;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
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
class RenteeRepositoryTest {

    @Autowired
    private RenteeRepository renteeRepository;

    private final int PAGE_SIZE = 2;


    private Rentee sampleRentee1() {
        return Rentee.builder()
                .thumbnail(
                        RenteeThumbnail.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testRentee1")
                .type(RenteeType.WEB)
                .authorName("testAuthor1")
                .publisherName("testPublisher1")
                .publishedDate(LocalDate.of(1999, 10, 18))
                .note("test1")
                .build();
    }

    private Rentee sampleRentee2() {
        return Rentee.builder()
                .thumbnail(
                        RenteeThumbnail.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testRentee2")
                .type(RenteeType.WEB)
                .authorName("testAuthor2")
                .publisherName("testPublisher2")
                .publishedDate(LocalDate.of(2000, 10, 18))
                .note("test2")
                .build();
    }

    private Rentee sampleRentee3() {
        return Rentee.builder()
                .thumbnail(
                        RenteeThumbnail.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testRentee3")
                .type(RenteeType.WEB)
                .authorName("testAuthor3")
                .publisherName("testPublisher3")
                .publishedDate(LocalDate.of(2001, 10, 18))
                .note("test3")
                .build();
    }

    private Rentee sampleRentee4() {
        return Rentee.builder()
                .thumbnail(
                        RenteeThumbnail.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testRentee4")
                .type(RenteeType.APP)
                .authorName("testAuthor4")
                .publisherName("testPublisher4")
                .publishedDate(LocalDate.of(2002, 10, 18))
                .note("test4")
                .build();
    }

    private Rentee sampleRentee5() {
        return Rentee.builder()
                .thumbnail(
                        RenteeThumbnail.builder()
                                .filePath("images/testImage.jpg")
                                .build()
                )
                .title("testRentee5")
                .type(RenteeType.APP)
                .authorName("testAuthor5")
                .publisherName("testPublisher5")
                .publishedDate(LocalDate.of(2003, 10, 18))
                .note("test5")
                .build();
    }

    @BeforeAll
    void init() {
        renteeRepository.save(sampleRentee4());
        renteeRepository.save(sampleRentee5());
        renteeRepository.save(sampleRentee1());
        renteeRepository.save(sampleRentee2());
        renteeRepository.save(sampleRentee3());
    }



    @DisplayName("getRecentRenteeWithPaging 작동 테스트 (기본, 1페이지)")
    @Test
    void test_getRecentRenteeWithPaging_firstPage() {
        // given
        final Long lastId = null;

        // when
        List<Rentee> rentees = renteeRepository.getRenteesWithPaging(PAGE_SIZE, lastId);

        // then
        assertThat(rentees)
                .hasSize(2)
                .extracting(Rentee::getName)
                .containsExactly("testRentee3", "testRentee2");
    }
//
//    @DisplayName("getRecentRenteeWithPaging 작동 테스트 (마지막, 3페이지)")
//    @Test
//    void test_getRecentRenteeWithPaging_lastPage() {
//        // given
//        final Long lastId = 2L;
//
//        // when
//        List<Rentee> rentees = renteeRepository.getRenteesWithPaging(PAGE_SIZE, lastId);
//
//        // then
//        assertThat(rentees)
//                .hasSize(1)
//                .extracting(Rentee::getTitle)
//                .containsExactly("testRentee4");
//    }
//
//    @DisplayName("getRenteeByTypeWithPaging 작동 테스트 (기본, 1페이지)")
//    @Test
//    void test_getRenteeByTypeWithPaging_firstPage() {
//        // given
//        final Long lastId = null;
//        final RenteeType renteeType = RenteeType.WEB;
//
//        // when
//        List<Rentee> rentees = renteeRepository.getRenteesByTypeEqualBySortWithPaging(renteeType, PAGE_SIZE, lastId);
//
//        // then
//        assertThat(rentees)
//                .hasSize(2)
//                .extracting(Rentee::getType)
//                .contains(RenteeType.WEB);
//    }
//
//    @DisplayName("getRenteeByTypeWithPaging 작동 테스트 (마지막, 3페이지)")
//    @Test
//    void test_getRenteeByTypeWithPaging_lastPage() {
//        // given
//        final Long lastId = 4L;
//        final RenteeType renteeType = RenteeType.WEB;
//
//        // when
//        List<Rentee> rentees = renteeRepository.getRenteesByTypeEqualBySortWithPaging(renteeType, PAGE_SIZE, lastId);
//
//        // then
//        assertThat(rentees)
//                .hasSize(1)
//                .extracting(Rentee::getType)
//                .contains(RenteeType.WEB);
//    }
//
//    @DisplayName("searchRenteeByKeyword 작동 테스트 (불완전한 키워드)")
//    @Test
//    void test_searchRenteeByKeyword_uncompletedKeyword() {
//        // given
//        final String keyword = "test";
//
//        // when
//        List<Rentee> rentees = renteeRepository.searchRentee(keyword);
//
//        // then
//        assertThat(rentees)
//                .extracting(Rentee::getTitle)
//                .containsExactlyInAnyOrder("testRentee1", "testRentee2", "testRentee3", "testRentee4", "testRentee5");
//    }
//
//    @DisplayName("searchRenteeByKeyword 작동 테스트 (완전한 키워드)")
//    @Test
//    void test_searchRenteeByKeyword_completeKeyword() {
//        // given
//        final String keyword = "testRentee1";
//
//        // when
//        List<Rentee> rentees = renteeRepository.searchRentee(keyword);
//
//        // then
//        assertThat(rentees)
//                .hasSize(1)
//                .extracting(Rentee::getTitle)
//                .containsExactlyInAnyOrder("testRentee1");
//    }
}
