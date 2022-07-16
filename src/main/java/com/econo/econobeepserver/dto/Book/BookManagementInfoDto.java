package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.BookRental.BookRental;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Setter
@NoArgsConstructor
public class BookManagementInfoDto {

    private Long id;
    private String bookCoverImageUrl;
    private String title;
    private RenteeType type;
    private String authorName;
    private String publisherName;
    private LocalDate publishedDate;
    private RentState rentState;
    private String recentRenter;
    private Long recentRentalEpochSecond;
    private String note;

    public BookManagementInfoDto(Book book) {
        this.id = book.getId();
        // TODO: code to generate BookCoverImageUrl
//        this.bookCoverImageUrl =
        this.title = book.getTitle();
        this.type = book.getType();
        this.authorName = book.getAuthorName();
        this.publisherName = book.getPublisherName();
        this.publishedDate = book.getPublishedDate();
        this.rentState = book.getRentState();

        BookRental recentBookRental = book.getRentalHistories().get(book.getRentalHistories().size() - 1);
        // TODO: userId가 반환되고 있음. 차후에 userApiServer 연결필요.
        this.recentRenter = recentBookRental.getUserId().toString();
        this.recentRentalEpochSecond = recentBookRental.getRentalDateTime().toEpochSecond(ZoneOffset.of("+09:00"));
        this.note = book.getNote();
    }
}
