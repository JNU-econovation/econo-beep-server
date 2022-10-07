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
    private String authorName;

    @NotNull
    private String publisherName;

    @NotNull
    private Long publishedDateEpochSecond;

    @NotNull
    private MultipartFile thumbnail;

    private String note;


    @Builder
    public BookSaveDto(String title, RenteeType type, String authorName, String publisherName, Long publishedDateEpochSecond, MultipartFile thumbnail, String note) {
        this.title = title;
        this.type = type;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishedDateEpochSecond = publishedDateEpochSecond;
        this.thumbnail = thumbnail;
        this.note = note;
    }

    public BookSaveDto(Rentee rentee) {
        this.title = rentee.getName();
        this.type = rentee.getType();
        this.authorName = rentee.getAuthorName();
        this.publisherName = rentee.getPublisherName();
        this.publishedDateEpochSecond = toEpochSecond(rentee.getPublishedDate());
        this.note = rentee.getNote();
    }


    public Rentee toEntity() {
        return Rentee.builder()
                .title(title)
                .type(type)
                .authorName(authorName)
                .publisherName(publisherName)
                .publishedDate(toLocalDate(publishedDateEpochSecond))
                .note(note)
                .build();
    }
}
