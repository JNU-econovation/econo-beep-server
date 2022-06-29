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
    @OneToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @NotNull
    private String filePath;

    @NotNull
    private Long fileSize;

    @Builder
    public EquipmentImage(Equipment equipment, String filePath, Long fileSize) {
        this.equipment = equipment;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
