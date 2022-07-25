package com.econo.econobeepserver.dto.Equipment;

import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentElementDto {

    private Long id;
    private String name;
    private RenteeType type;
    private String equipmentImageUrl;
    private RentState rentState;


    public EquipmentElementDto(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.type = equipment.getType();
        this.equipmentImageUrl = "/equipment/" + equipment.getId() + "/image";
        this.rentState = equipment.getRentState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EquipmentElementDto)) return false;
        EquipmentElementDto that = (EquipmentElementDto) o;
        return id.equals(that.id) && equipmentImageUrl.equals(that.equipmentImageUrl) && name.equals(that.name) && type == that.type && rentState == that.rentState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
