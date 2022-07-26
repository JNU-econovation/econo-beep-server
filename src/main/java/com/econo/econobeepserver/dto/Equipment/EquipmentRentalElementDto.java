package com.econo.econobeepserver.dto.Equipment;

import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRental;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentRentalElementDto {

    private String renterName;
    private Long rentalEpochSecond;
    private Long returnEpochSecond;

    public EquipmentRentalElementDto(EquipmentRental equipmentRental) {
        this.renterName = equipmentRental.getRenterName();
        this.rentalEpochSecond = toEpochSecond(equipmentRental.getRentalDateTime());
    }
}
