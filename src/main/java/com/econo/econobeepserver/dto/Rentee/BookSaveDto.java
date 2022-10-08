package com.econo.econobeepserver.dto.Rentee;

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
    private String title;

    @NotNull
    private RenteeType type;

    @NotNull
    private String bookAuthorName;

    @NotNull
    private String bookPublisherName;

    @NotNull
    private Long bookPublishedDateEpochSecond;

    @NotNull
    private MultipartFile thumbnail;

    private String note;


    @Builder
    public BookSaveDto(String title, RenteeType type, String bookAuthorName, String bookPublisherName, Long bookPublishedDateEpochSecond, MultipartFile thumbnail, String note) {
        this.title = title;
        this.type = type;
        this.bookAuthorName = bookAuthorName;
        this.bookPublisherName = bookPublisherName;
        this.bookPublishedDateEpochSecond = bookPublishedDateEpochSecond;
        this.thumbnail = thumbnail;
        this.note = note;
    }

    public BookSaveDto(Rentee rentee) {
        this.title = rentee.getName();
        this.type = rentee.getType();
        this.bookAuthorName = rentee.getBookAuthorName();
        this.bookPublisherName = rentee.getBookPublisherName();
        this.bookPublishedDateEpochSecond = toEpochSecond(rentee.getBookPublishedDate());
        this.note = rentee.getNote();
    }


    public Rentee toEntity() {
        return Rentee.builder()
                .name(title)
                .type(type)
                .bookAuthorName(bookAuthorName)
                .bookPublisherName(bookPublisherName)
                .bookPublishedDate(toLocalDate(bookPublishedDateEpochSecond))
                .note(note)
                .build();
    }
}
