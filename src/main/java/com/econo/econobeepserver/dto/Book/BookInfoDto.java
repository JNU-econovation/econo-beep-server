package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@NoArgsConstructor
public class BookInfoDto {

    private Long id;
    private String bookCoverImageUrl;
    private List<BookRentalElementDto> rentalHistories;
    private String title;
    private RenteeType type;
    private String authorName;
    private String publisherName;
    private LocalDate publishedDate;
    private RentState rentState;
    private int rentCount;
    private String note;


    public BookInfoDto(Book book) {
        this.id = book.getId();
        // TODO: code to generate BookCoverImageUrl
        this.bookCoverImageUrl = "TODO";
        this.rentalHistories = book.getRentalHistories().stream().map(BookRentalElementDto::new).collect(Collectors.toList());
        this.title = book.getTitle();
        this.type = book.getType();
        this.authorName = book.getAuthorName();
        this.publisherName = book.getPublisherName();
        this.publishedDate = book.getPublishedDate();
        this.rentState = book.getRentState();
        this.rentCount = book.getRentCount();
        this.note = book.getNote();
    }
}
