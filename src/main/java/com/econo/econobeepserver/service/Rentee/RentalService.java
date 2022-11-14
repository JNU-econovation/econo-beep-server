package com.econo.econobeepserver.service.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.domain.Rental.RentalRepository;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.service.User.EconoIDP;
import com.econo.econobeepserver.dto.User.UserInfoDto;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import com.econo.econobeepserver.exception.UnreturnableException;
import com.econo.econobeepserver.service.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final RenteeService renteeService;
    private final UserService userService;


    private Rental getRecentRentalByRenteeId(long renteeId) {
        return rentalRepository.findFirstByRentee_IdOrderByCreatedDateDesc(renteeId).orElseThrow(UnrentableException::new);
    }

    private void validateRentableRentee(final Rentee rentee) {
        if (rentee.getRentState() != RentState.RENTABLE) {
            throw new UnrentableException();
        }
    }

    @Transactional
    public void rentRenteeByRenteeId(Long renteeId, String accessToken) {
        User user = userService.getUserByAccessToken(accessToken);

        Rentee rentee = renteeService.getRenteeById(renteeId);
        validateRentableRentee(rentee);

        Rental rental = Rental.builder()
                .rentee(rentee)
                .renter(user)
                .build();
        rentalRepository.save(rental);
    }


    private void validateReturnableRentee(final Rentee rentee) {
        if (rentee.getRentState() != RentState.RENTED) {
            throw new UnreturnableException();
        }
    }

    private void validateRenter(final Rental rental, final UserInfoDto userInfoDto) {
        if (
                !Objects.equals(rental.getRenter().getId(), userInfoDto.getId())
        ) {
            throw new NotRenterException();
        }
    }

    @Transactional
    public void returnRenteeByRenteeId(Long renteeId, String accessToken) {
        Rentee rentee = renteeService.getRenteeById(renteeId);
        validateReturnableRentee(rentee);

        Rental rental = getRecentRentalByRenteeId(renteeId);
        UserInfoDto userInfoDto = userService.getUserInfoDtoByAccessToken(accessToken);
        validateRenter(rental, userInfoDto);

        rental.returnRentee();
    }
}
