package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BookSaveDto {

    private String title;
    private RenteeType type;
    private String authorName;
    private String publisherName;
    private LocalDate publishedDate;
    private String note;
    // TODO : code to receive uploaded picture


    public Book toEntity() {
        return Book.builder()
                .title(title)
                .type(type)
                .authorName(authorName)
                .publisherName(publisherName)
                .publishedDate(publishedDate)
                .note(note)
                .build();
    }

    public BookSaveDto(Book book) {
        this.title = book.getTitle();
        this.type = book.getType();
        this.authorName = book.getAuthorName();
        this.publisherName = book.getPublisherName();
        this.publishedDate = book.getPublishedDate();
        this.note = book.getNote();
    }
}
