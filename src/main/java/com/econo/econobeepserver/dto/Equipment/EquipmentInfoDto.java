package com.econo.econobeepserver.dto.Equipment;

import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class EquipmentInfoDto {

    private Long id;
    private String thumbnailUrl;
    private List<EquipmentRentalElementDto> rentalHistories;
    private String title;
    private RenteeType type;
    private RentState rentState;
    private int rentCount;
    private String note;


    public EquipmentInfoDto(Equipment equipment) {
        this.id = equipment.getId();
        this.thumbnailUrl = "/equipment/" + equipment.getId() + "/image";
        this.rentalHistories = equipment.getRentalHistories().stream().map(EquipmentRentalElementDto::new).collect(Collectors.toList());
        this.title = equipment.getTitle();
        this.type = equipment.getType();
        this.rentState = equipment.getRentState();
        this.rentCount = equipment.getRentCount();
        this.note = equipment.getNote();
    }
}
