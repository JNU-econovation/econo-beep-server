package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rentee.BookArea;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import com.econo.econobeepserver.util.EpochTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;
import static com.econo.econobeepserver.util.EpochTime.toLocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RenteeSaveDto {

    private MultipartFile thumbnail;

    private RenteeType type;

    private String name;

    private BookArea bookArea;

    private String bookAuthorName;

    private String bookPublisherName;

    private Long bookPublishedDateEpochSecond;

    private String note;


    @Builder
    public RenteeSaveDto(MultipartFile thumbnail, RenteeType type, String name,
                         BookArea bookArea, String bookAuthorName, String bookPublisherName, Long bookPublishedDateEpochSecond,
                         String note) {
        this.thumbnail = thumbnail;
        this.type = type;
        this.name = name;
        this.bookArea = bookArea;
        this.bookAuthorName = bookAuthorName;
        this.bookPublisherName = bookPublisherName;
        this.bookPublishedDateEpochSecond = bookPublishedDateEpochSecond;
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Rentee that = (Rentee) o;
        return getType() == that.getType() && getName().equals(that.getName()) && getBookArea() == that.getBookArea() &&
                Objects.equals(getBookAuthorName(), that.getBookAuthorName()) && Objects.equals(getBookPublisherName(), that.getBookPublisherName()) &&
                Objects.equals(EpochTime.toLocalDate(bookPublishedDateEpochSecond), that.getBookPublishedDate()) &&
                Objects.equals(getNote(), that.getNote());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName());
    }


    public RenteeSaveDto(BookSaveDto bookSaveDto) {
        this.thumbnail = bookSaveDto.getThumbnail();
        this.type = RenteeType.BOOK;
        this.name = bookSaveDto.getName();
        this.bookArea = bookSaveDto.getBookArea();
        this.bookAuthorName = bookSaveDto.getBookAuthorName();
        this.bookPublisherName = bookSaveDto.getBookPublisherName();
        this.bookPublishedDateEpochSecond = bookSaveDto.getBookPublishedDateEpochSecond();
        this.note = bookSaveDto.getNote();
    }

    public RenteeSaveDto(DeviceSaveDto deviceSaveDto) {
        this.thumbnail = deviceSaveDto.getThumbnail();
        this.type = RenteeType.DEVICE;
        this.name = deviceSaveDto.getName();
        this.note = deviceSaveDto.getNote();
    }


    public Rentee toEntity() {
        return Rentee.builder()
                .type(type)
                .name(name)
                .bookArea(bookArea)
                .bookAuthorName(bookAuthorName)
                .bookPublisherName(bookPublisherName)
                .bookPublishedDate(toLocalDate(bookPublishedDateEpochSecond))
                .note(note)
                .build();
    }
}
