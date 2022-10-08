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
public class RenteeSaveDto {

    private String title;

    private RenteeType type;

    private String bookAuthorName;

    private String bookPublisherName;

    private Long bookPublishedDateEpochSecond;

    private MultipartFile thumbnail;

    private String note;


    @Builder
    public RenteeSaveDto(String title, RenteeType type, String bookAuthorName, String bookPublisherName, Long bookPublishedDateEpochSecond, MultipartFile thumbnail, String note) {
        this.title = title;
        this.type = type;
        this.bookAuthorName = bookAuthorName;
        this.bookPublisherName = bookPublisherName;
        this.bookPublishedDateEpochSecond = bookPublishedDateEpochSecond;
        this.thumbnail = thumbnail;
        this.note = note;
    }

    public RenteeSaveDto(BookSaveDto bookSaveDto) {
        this.title = bookSaveDto.getTitle();
        this.type = bookSaveDto.getType();
        this.bookAuthorName = bookSaveDto.getBookAuthorName();
        this.bookPublisherName = bookSaveDto.getBookPublisherName();
        this.bookPublishedDateEpochSecond = bookSaveDto.getBookPublishedDateEpochSecond();
        this.thumbnail = bookSaveDto.getThumbnail();
        this.note = bookSaveDto.getNote();
    }

    public RenteeSaveDto(EquipmentSaveDto equipmentSaveDto) {
        this.title = equipmentSaveDto.getTitle();
        this.type = equipmentSaveDto.getType();
        this.thumbnail = equipmentSaveDto.getThumbnail();
        this.note = equipmentSaveDto.getNote();
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
