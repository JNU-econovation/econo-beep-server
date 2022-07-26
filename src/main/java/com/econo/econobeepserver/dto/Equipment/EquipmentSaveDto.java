package com.econo.econobeepserver.dto.Equipment;

import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.RenteeType;
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

    @NotNull
    private MultipartFile equipmentImage;

    private String note;


    @Builder
    public EquipmentSaveDto(String title, RenteeType type, MultipartFile equipmentImage, String note) {
        this.title = title;
        this.type = type;
        this.equipmentImage = equipmentImage;
        this.note = note;
    }

    public EquipmentSaveDto(Equipment equipment) {
        this.title = equipment.getTitle();
        this.type = equipment.getType();
        this.note = equipment.getNote();
    }


    public Equipment toEntity() {
        return Equipment.builder()
                .title(title)
                .type(type)
                .note(note)
                .build();
    }
}
