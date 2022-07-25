package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.BookRental.BookRental;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZoneOffset;

@Getter
@Setter
@NoArgsConstructor
public class BookRentalElementDto {

    // TODO: userId가 반환되고 있음. 차후에 userApiServer와 연결필요.
    private String userName;
    private Long rentalEpochSecond;
    private Long returnEpochSecond;

    public BookRentalElementDto(BookRental bookRental) {
        this.userName = bookRental.getRenterName().toString();
        this.rentalEpochSecond = bookRental.getRentalDateTime().toEpochSecond(ZoneOffset.of("+09:00"));
    }
}
