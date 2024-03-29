package com.econo.econobeepserver.service.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.domain.Rental.RentalRepository;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.dto.Rentee.RenteeElementDto;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import com.econo.econobeepserver.exception.UnreturnableException;
import com.econo.econobeepserver.service.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public void rentRenteeByRenteeIdAndUserId(Long renteeId, Long userId) {
        User user = userService.getUserByUserId(userId);

        Rentee rentee = renteeService.getRenteeByRenteeId(renteeId);
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

    private void validateRenter(final User renter, final long userId) {
        if (!renter.getId().equals(userId)) {
            throw new NotRenterException();
        }
    }

    @Transactional
    public void returnRenteeByRenteeId(Long renteeId, Long userId) {
        Rentee rentee = renteeService.getRenteeByRenteeId(renteeId);
        validateReturnableRentee(rentee);

        Rental rental = getRecentRentalByRenteeId(renteeId);
        validateRenter(rental.getRenter(), userId);

        rental.returnRentee();
    }

    public List<RenteeElementDto> getRentingRenteesByUserId(long userId) {
        List<Rental> rentals = rentalRepository.findByReturnDateTimeIsNullAndRenter_Id(userId);

        return rentals.stream().map((rental -> new RenteeElementDto(rental.getRentee()))).collect(Collectors.toList());
    }

    public List<RenteeElementDto> getReturnedRenteeByUserId(long userId) {
        List<Rental> rentals = rentalRepository.findByReturnDateTimeIsNotNullAndRenter_Id(userId);

        return rentals.stream().map((rental -> new RenteeElementDto(rental.getRentee()))).collect(Collectors.toList());
    }
}
