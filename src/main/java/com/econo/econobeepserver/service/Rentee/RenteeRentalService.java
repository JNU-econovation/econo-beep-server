package com.econo.econobeepserver.service.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.User.UserApi;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import com.econo.econobeepserver.exception.UnreturnableException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RenteeRentalService {

    private final RenteeService renteeService;
    private UserApi userApi;


    private void validateRentableRentee(final Rentee rentee) {
        if (rentee.getRentState() != RentState.RENTABLE) {
            throw new UnrentableException();
        }
    }

    @Transactional
    public void rentRenteeById(Long id, String pinCode) {
        userApi.validatePinCode(pinCode);

        Rentee rentee = renteeService.getRenteeById(id);
        validateRentableRentee(rentee);

        UserInfoDto userInfoDto = userApi.getUserInfoDtoByPinCode(pinCode);
        Rental rental = Rental.builder()
                .renterId(userInfoDto.getId())
                .renterName(userInfoDto.getUserName())
                .build();

        rentee.rentRentee(rental);
    }


    private void validateReturnableRentee(final Rentee rentee) {
        if (rentee.getRentals().isEmpty() || rentee.getRentState() != RentState.RENTED) {
            throw new UnreturnableException();
        }
    }

    private void validateRenter(final Rental rental, final Long renterId) {
        if (
                !Objects.equals(rental.getRenterId(), renterId)
        ) {
            throw new NotRenterException();
        }
    }

    @Transactional
    public void returnRenteeById(Long id, String pinCode) {
        userApi.validatePinCode(pinCode);

        Rentee rentee = renteeService.getRenteeById(id);
        validateReturnableRentee(rentee);

        Rental rental = rentee.getRentals().get(rentee.getRentals().size() - 1);
        UserInfoDto userInfoDto = userApi.getUserInfoDtoByPinCode(pinCode);
        validateRenter(rental, userInfoDto.getId());

        rental.returnRentee();
        rentee.returnRentee();
    }
}
