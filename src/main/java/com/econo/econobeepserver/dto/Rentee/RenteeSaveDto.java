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

    private String authorName;

    private String publisherName;

    private Long publishedDateEpochSecond;

    private MultipartFile thumbnail;

    private String note;


    @Builder
    public RenteeSaveDto(String title, RenteeType type, String authorName, String publisherName, Long publishedDateEpochSecond, MultipartFile thumbnail, String note) {
        this.title = title;
        this.type = type;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishedDateEpochSecond = publishedDateEpochSecond;
        this.thumbnail = thumbnail;
        this.note = note;
    }

    public RenteeSaveDto(BookSaveDto bookSaveDto) {
        this.title = bookSaveDto.getTitle();
        this.type = bookSaveDto.getType();
        this.authorName = bookSaveDto.getAuthorName();
        this.publisherName = bookSaveDto.getPublisherName();
        this.publishedDateEpochSecond = bookSaveDto.getPublishedDateEpochSecond();
        this.thumbnail = bookSaveDto.getThumbnail();
        this.note = bookSaveDto.getNote();
    }

    public RenteeSaveDto(EquipmentSaveDto equipmentSaveDto) {
        this.title = equipmentSaveDto.getTitle();
        this.type = equipmentSaveDto.getType();
        this.note = equipmentSaveDto.getNote();
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
