package com.econo.econobeepserver.domain.Equipment;

import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRental;
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
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne(mappedBy = "equipment")
    private EquipmentImage equipmentImage;

    @NotNull
    @OneToMany(mappedBy = "equipment")
    @JsonManagedReference
    private List<EquipmentRental> rentalHistories = new ArrayList<>();

    @NotNull
    private String name;

    @NotNull
    private EquipmentType type;

    @NotNull
    private boolean isRented = false;

    @NotNull
    private int rentCount = 0;

    @Builder
    public Equipment(EquipmentImage equipmentImage, String name, EquipmentType type) {
        this.equipmentImage = equipmentImage;
        this.name = name;
        this.type = type;
    }
}
