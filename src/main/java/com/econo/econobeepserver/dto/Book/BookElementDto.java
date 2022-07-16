package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class BookElementDto {

    private Long id;
    private String bookCoverImageUrl;
    private String title;
    private RenteeType type;
    private String authorName;
    private RentState rentState;


    public BookElementDto(Book book) {
        this.id = book.getId();
        // TODO: code to generate BookCoverImageUrl
//        this.bookCoverImageUrl = book.getbookCoverImageUrl();
        this.title = book.getTitle();
        this.type = book.getType();
        this.authorName = book.getAuthorName();
        this.rentState = book.getRentState();
    }
}
