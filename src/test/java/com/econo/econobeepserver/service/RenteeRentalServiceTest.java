package com.econo.econobeepserver.service;

import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RenteeThumbnail;
import com.econo.econobeepserver.domain.Rentee.RenteeRepository;
import com.econo.econobeepserver.domain.Rental.RentalRepository;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import com.econo.econobeepserver.domain.User.UserApi;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotFoundPinCodeException;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.exception.WrongFormatPinCodeException;
import com.econo.econobeepserver.service.Rentee.RenteeRentalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest
class RenteeRentalServiceTest {

    @Autowired
    @InjectMocks
    private RenteeRentalService renteeRentalService;

    @MockBean
    private UserApi userApi;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RenteeRepository renteeRepository;


    private final String SAMPLE_PINCODE = "1234";

    private final String WRONG_FORMAT_PINCODE = "1A34";

    private final String SHORT_LENGTH_PINCODE = "1";

    private final String LONG_LENGTH_PINCODE = "1234567";

    private final String NOT_FOUND_PINCODE = "9876";


    private Rentee sampleRentee() {
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

    private UserInfoDto sampleUser() {
        return UserInfoDto.builder()
                .uid(1L)
                .name("tester")
                .pinCode("1234")
                .build();
    }

    @AfterEach
    void reset() {
        rentalRepository.deleteAll();
        renteeRepository.deleteAll();
    }


    @DisplayName("rentRenteeById 작동 테스트")
    @Test
    @Transactional
    void test_rentRenteeById() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);


        // when & then
        Rentee rentee = renteeRepository.findById(renteeId).get();

        assertDoesNotThrow(() -> renteeRentalService.rentRenteeById(renteeId, SAMPLE_PINCODE));
        assertEquals(RentState.RENTED, rentee.getRentState());
        assertEquals(1, rentee.getRentalHistories().size());
        assertEquals(1, rentee.getRentCount());

        assertEquals(1L, rentalRepository.count());
        Rental rental = rentalRepository.findByRentee_Id(renteeId).get();
        assertEquals(renteeId, rental.getRentee().getId());
    }


    @DisplayName("rentRenteeById 오류 대응 테스트 (존재하지 않는 RenteeId)")
    @Test
    void test_rentRenteeById_notExistRenteeIdParams() {
        // given
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);


        // when & then
        assertThrows(NotFoundRenteeException.class, () -> renteeRentalService.rentRenteeById(100L, SAMPLE_PINCODE));
    }


    @DisplayName("rentRenteeById 오류 대응 테스트 (글자가 포함된 핀코드)")
    @Test
    void test_rentRenteeById_wrongCharacterPinCodeParams() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doThrow(WrongFormatPinCodeException.class)
                .when(userApi)
                .validatePinCode(WRONG_FORMAT_PINCODE);
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(WRONG_FORMAT_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> renteeRentalService.rentRenteeById(renteeId, WRONG_FORMAT_PINCODE));
    }


    @DisplayName("rentRenteeById 오류 대응 테스트 (길이가 짧은 핀코드)")
    @Test
    void test_rentRenteeById_shortLengthPinCodeParams() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doThrow(WrongFormatPinCodeException.class)
                .when(userApi)
                .validatePinCode(SHORT_LENGTH_PINCODE);
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SHORT_LENGTH_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> renteeRentalService.rentRenteeById(renteeId, SHORT_LENGTH_PINCODE));
    }


    @DisplayName("rentRenteeById 오류 대응 테스트 (길이가 긴 핀코드)")
    @Test
    void test_rentRenteeById_longLengthPinCodeParams() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doThrow(WrongFormatPinCodeException.class)
                .when(userApi)
                .validatePinCode(LONG_LENGTH_PINCODE);
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(LONG_LENGTH_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> renteeRentalService.rentRenteeById(renteeId, LONG_LENGTH_PINCODE));
    }


    @DisplayName("rentRenteeById 오류 대응 테스트 (존재하지 않는 핀코드 예외)")
    @Test
    void test_rentRenteeById_notFoundPinCode() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doThrow(NotFoundPinCodeException.class)
                .when(userApi)
                .getUserInfoDtoByPinCode(NOT_FOUND_PINCODE);


        // when & then
        assertThrows(NotFoundPinCodeException.class, () -> renteeRentalService.rentRenteeById(renteeId, NOT_FOUND_PINCODE));
    }


    @DisplayName("returnRenteeById 작동 테스트")
    @Test
    @Transactional
    void test_returnRenteeById() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);

        assertDoesNotThrow(() -> renteeRentalService.rentRenteeById(renteeId, SAMPLE_PINCODE));


        // when & then
        Rentee rentee = renteeRepository.findById(renteeId).get();

        assertDoesNotThrow(() -> renteeRentalService.returnRenteeById(renteeId, SAMPLE_PINCODE));
        assertEquals(RentState.RENTABLE, rentee.getRentState());

        assertEquals(1L, rentalRepository.count());
        Rental rental = rentalRepository.findAll().get(0);
        assertNotNull(rental.getReturnDateTime());

        assertEquals(1, rentee.getRentalHistories().size());
    }


    @DisplayName("returnRenteeById 오류 대응 테스트 (존재하지 않는 RenteeId)")
    @Test
    void test_returnRenteeById_notExistRenteeIdParams() {
        // when & then
        assertThrows(NotFoundRenteeException.class, () -> renteeRentalService.returnRenteeById(100L, SAMPLE_PINCODE));
    }


    @DisplayName("returnRenteeById 오류 대응 테스트 (글자가 포함된 핀코드)")
    @Test
    void test_returnRenteeById_wrongCharacterPinCodeParams() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doThrow(WrongFormatPinCodeException.class)
                .when(userApi)
                .validatePinCode(WRONG_FORMAT_PINCODE);
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(WRONG_FORMAT_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> renteeRentalService.returnRenteeById(renteeId, WRONG_FORMAT_PINCODE));
    }


    @DisplayName("returnRenteeById 오류 대응 테스트 (길이가 짧은 핀코드)")
    @Test
    void test_returnRenteeById_shortLengthPinCodeParams() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doThrow(WrongFormatPinCodeException.class)
                .when(userApi)
                .validatePinCode(SHORT_LENGTH_PINCODE);
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SHORT_LENGTH_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> renteeRentalService.returnRenteeById(renteeId, SHORT_LENGTH_PINCODE));
    }


    @DisplayName("returnRenteeById 오류 대응 테스트 (길이가 긴 핀코드)")
    @Test
    void test_returnRenteeById_longLengthPinCodeParams() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();
        doThrow(WrongFormatPinCodeException.class)
                .when(userApi)
                .validatePinCode(LONG_LENGTH_PINCODE);
        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(LONG_LENGTH_PINCODE);


        // when & then
        assertThrows(WrongFormatPinCodeException.class, () -> renteeRentalService.returnRenteeById(renteeId, LONG_LENGTH_PINCODE));
    }


    @DisplayName("returnRenteeById 오류 대응 테스트 (존재하지 않는 핀코드 예외)")
    @Test
    @Transactional
    void test_returnRenteeById_notFoundPinCode() {
        // given
        long renteeId = renteeRepository.save(sampleRentee()).getId();

        doReturn(sampleUser())
                .when(userApi)
                .getUserInfoDtoByPinCode(SAMPLE_PINCODE);
        renteeRentalService.rentRenteeById(renteeId, SAMPLE_PINCODE);

        doThrow(NotFoundPinCodeException.class)
                .when(userApi)
                .getUserInfoDtoByPinCode(NOT_FOUND_PINCODE);


        // when & then
        assertThrows(NotFoundPinCodeException.class, () -> renteeRentalService.returnRenteeById(renteeId, NOT_FOUND_PINCODE));
    }

}
