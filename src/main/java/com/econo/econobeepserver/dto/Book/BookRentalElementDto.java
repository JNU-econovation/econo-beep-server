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

    // TODO: userId가 반환되고 있음. 차후에 userApiServer와 연결필요.
    private String renterName;
    private Long rentalEpochSecond;
    private Long returnEpochSecond;

    public BookRentalElementDto(BookRental bookRental) {
        this.renterName = bookRental.getRenterName();
        this.rentalEpochSecond = toEpochSecond(bookRental.getRentalDateTime());
    }
}
