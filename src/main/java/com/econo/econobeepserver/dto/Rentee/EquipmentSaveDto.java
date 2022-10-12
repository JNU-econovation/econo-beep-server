package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rentee.Rentee;
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
    private MultipartFile thumbnail;

    @NotNull
    private String name;

    private String note;


    @Builder
    public EquipmentSaveDto(MultipartFile thumbnail, String name, String note) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.note = note;
    }

    public EquipmentSaveDto(Rentee rentee) {
        this.name = rentee.getName();
        this.note = rentee.getNote();
    }
}