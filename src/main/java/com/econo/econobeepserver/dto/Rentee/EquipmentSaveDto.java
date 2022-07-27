package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentSaveDto {

    @NotNull
    private String title;

    @NotNull
    private RenteeType type;

    private MultipartFile thumbnail;

    private String note;


    @Builder
    public EquipmentSaveDto(String title, RenteeType type, MultipartFile thumbnail, String note) {
        this.title = title;
        this.type = type;
        this.thumbnail = thumbnail;
        this.note = note;
    }

    public EquipmentSaveDto(Rentee rentee) {
        this.title = rentee.getTitle();
        this.type = rentee.getType();
        this.note = rentee.getNote();
    }


    public Rentee toEntity() {
        return Rentee.builder()
                .title(title)
                .type(type)
                .note(note)
                .build();
    }
}
