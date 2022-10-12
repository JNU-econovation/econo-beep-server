package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rentee.BookArea;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
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
    private MultipartFile thumbnail;

    @NotNull
    private String name;

    @NotNull
    private BookArea bookArea;

    @NotNull
    private String bookAuthorName;

    @NotNull
    private String bookPublisherName;

    @NotNull
    private Long bookPublishedDateEpochSecond;

    private String note;


    @Builder
    public BookSaveDto(String name, BookArea bookArea, String bookAuthorName, String bookPublisherName, Long bookPublishedDateEpochSecond, MultipartFile thumbnail, String note) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.bookArea = bookArea;
        this.bookAuthorName = bookAuthorName;
        this.bookPublisherName = bookPublisherName;
        this.bookPublishedDateEpochSecond = bookPublishedDateEpochSecond;
        this.note = note;
    }

    public BookSaveDto(Rentee rentee) {
        this.name = rentee.getName();
        this.bookArea = rentee.getBookArea();
        this.bookAuthorName = rentee.getBookAuthorName();
        this.bookPublisherName = rentee.getBookPublisherName();
        this.bookPublishedDateEpochSecond = toEpochSecond(rentee.getBookPublishedDate());
        this.note = rentee.getNote();
    }
}
