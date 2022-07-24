package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

    @NotNull
    private MultipartFile bookCoverImage;

    private String note;


    @Builder
    public BookSaveDto(String title, RenteeType type, String authorName, String publisherName, Long publishedDateEpochSecond, MultipartFile bookCoverImage, String note) {
        this.title = title;
        this.type = type;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishedDateEpochSecond = publishedDateEpochSecond;
        this.bookCoverImage = bookCoverImage;
        this.note = note;
    }


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
