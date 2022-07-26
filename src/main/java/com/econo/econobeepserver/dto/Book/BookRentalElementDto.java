package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.BookRental.BookRental;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Getter
@Setter
@NoArgsConstructor
public class BookRentalElementDto {

    private String renterName;
    private Long rentalEpochSecond;
    private Long returnEpochSecond;

    public BookRentalElementDto(BookRental bookRental) {
        this.renterName = bookRental.getRenterName();
        this.rentalEpochSecond = toEpochSecond(bookRental.getRentalDateTime());
    }
}
