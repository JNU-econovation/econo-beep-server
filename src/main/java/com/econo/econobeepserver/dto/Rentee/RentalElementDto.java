package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
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

    public RentalElementDto(Rental rental) {
        this.renterName = rental.getRenter().getName();
        this.renterProfileImage = rental.getRenter().getProfileImageUrl();
        this.rentalEpochSecond = toEpochSecond(rental.getRentalDateTime());
        this.returnEpochSecond = toEpochSecond(rental.getReturnDateTime());
    }
}
