package com.econo.econobeepserver.domain.Equipment;

import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRental;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.dto.Equipment.EquipmentSaveDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@SequenceGenerator(name = "RENTEE_SEQ_GENERATOR", sequenceName = "RENTEE_SEQ", initialValue = 1, allocationSize = 1)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RENTEE_SEQ_GENERATOR")
    private long id;

    @OneToOne
    @JoinColumn(name = "equipment_image_id")
    private EquipmentImage equipmentImage;

    @NotNull
    @OneToMany(mappedBy = "equipment")
    @JsonManagedReference
    private List<EquipmentRental> rentalHistories = new ArrayList<>();

    @NotNull
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RenteeType type;

    @NotNull
    private RentState rentState = RentState.RENTABLE;

    @NotNull
    private int rentCount = 0;

    private String note;


    @Builder
    public Equipment(EquipmentImage equipmentImage, String title, RenteeType type, String note) {
        this.equipmentImage = equipmentImage;
        this.title = title;
        this.type = type;
        this.note = note;
    }


    public void updateEquipment(EquipmentSaveDto equipmentSaveDto) {
        this.title = equipmentSaveDto.getTitle();
        this.type = equipmentSaveDto.getType();
        this.note = equipmentSaveDto.getNote();
    }

    public void rentEquipment(EquipmentRental equipmentRental) {
        rentState = RentState.RENTED;
        rentCount++;

        rentalHistories.add(equipmentRental);
        equipmentRental.setEquipment(this);
    }

    public void returnEquipment() {
        this.rentState = RentState.RENTABLE;
    }

    public void disableEquipment() {
        this.rentState = RentState.UNRENTABLE;
    }

    public void setEquipmentCoverImage(EquipmentImage equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public void clearAttributes() {
        equipmentImage = null;
        rentalHistories.clear();
    }
}
