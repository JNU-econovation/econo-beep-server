package com.econo.econobeepserver.domain.Equipment;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class EquipmentImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne(mappedBy = "equipmentImage")
    private Equipment equipment;

    @NotNull
    private String filePath;


    @Builder
    public EquipmentImage(Equipment equipment, String filePath) {
        this.equipment = equipment;
        this.filePath = filePath;
    }


    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
