package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.dto.User.UserRenterDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Getter
@Setter
@NoArgsConstructor
public class RentalElementDto {

    private String renterName;
    private String renterProfileImage;
    private Long rentalEpochSecond;
    private Long returnEpochSecond;

    public RentalElementDto(Rental rental, UserRenterDto userRenterDto) {
        this.renterName = userRenterDto.getUsername();
        this.renterProfileImage = userRenterDto.getProfileImageUrl();
        this.rentalEpochSecond = toEpochSecond(rental.getRentalDateTime());
        this.returnEpochSecond = toEpochSecond(rental.getReturnDateTime());
    }
}
