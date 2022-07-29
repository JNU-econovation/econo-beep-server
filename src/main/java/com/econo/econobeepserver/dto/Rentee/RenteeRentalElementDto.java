package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Getter
@Setter
@NoArgsConstructor
public class RenteeRentalElementDto {

    private String renterName;
    private Long rentalEpochSecond;
    private Long returnEpochSecond;

    public RenteeRentalElementDto(Rental rental) {
        this.renterName = rental.getRenterName();
        this.rentalEpochSecond = toEpochSecond(rental.getRentalDateTime());
        this.returnEpochSecond = toEpochSecond(rental.getReturnDateTime());
    }
}
