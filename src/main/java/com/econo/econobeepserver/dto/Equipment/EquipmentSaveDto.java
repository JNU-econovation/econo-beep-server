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
    private String name;

    @NotNull
    private RenteeType type;

    @NotNull
    private MultipartFile equipmentImage;

    private String note;


    @Builder
    public EquipmentSaveDto(String name, RenteeType type, MultipartFile equipmentImage, String note) {
        this.name = name;
        this.type = type;
        this.equipmentImage = equipmentImage;
        this.note = note;
    }

    public EquipmentSaveDto(Equipment equipment) {
        this.name = equipment.getName();
        this.type = equipment.getType();
        this.note = equipment.getNote();
    }


    public Equipment toEntity() {
        return Equipment.builder()
                .name(name)
                .type(type)
                .note(note)
                .build();
    }
}
