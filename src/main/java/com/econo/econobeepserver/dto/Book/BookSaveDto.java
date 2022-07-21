package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;
import static com.econo.econobeepserver.util.EpochTime.toLocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookSaveDto {

    @NotNull
    private String title;

    @NotNull
    private RenteeType type;

    @NotNull
    private String authorName;

    @NotNull
    private String publisherName;

    @NotNull
    private Long publishedDateEpochSecond;

    private String note;

    // TODO : code to receive uploaded picture


    public Book toEntity() {
        return Book.builder()
                .title(title)
                .type(type)
                .authorName(authorName)
                .publisherName(publisherName)
                .publishedDate(toLocalDate(publishedDateEpochSecond))
                .note(note)
                .build();
    }

    public BookSaveDto(Book book) {
        this.title = book.getTitle();
        this.type = book.getType();
        this.authorName = book.getAuthorName();
        this.publisherName = book.getPublisherName();
        this.publishedDateEpochSecond = toEpochSecond(book.getPublishedDate());
        this.note = book.getNote();
    }
}
