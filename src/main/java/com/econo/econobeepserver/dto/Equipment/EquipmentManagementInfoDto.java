package com.econo.econobeepserver.dto.Equipment;

import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.EquipmentRental.EquipmentRental;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Getter
@Setter
@NoArgsConstructor
public class EquipmentManagementInfoDto {

    private Long id;
    private String name;
    private RenteeType type;
    private String equipmentImageUrl;
    private String note;
    private RentState rentState;
    private String recentRenter;
    private Long recentRentalEpochSecond;

    public EquipmentManagementInfoDto(Equipment equipment) {
        this.id = equipment.getId();
        this.name = equipment.getName();
        this.type = equipment.getType();
        this.equipmentImageUrl = "/equipment/" + equipment.getId() + "/image";
        this.note = equipment.getNote();
        this.rentState = equipment.getRentState();

        final List<EquipmentRental> equipmentRentals = equipment.getRentalHistories();
        if (!equipmentRentals.isEmpty()) {
            final EquipmentRental equipmentRental = equipmentRentals.get(equipmentRentals.size() - 1);

            this.recentRenter = equipmentRental.getRenterName();
            this.recentRentalEpochSecond = toEpochSecond(equipmentRental.getRentalDateTime());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EquipmentManagementInfoDto)) return false;
        EquipmentManagementInfoDto that = (EquipmentManagementInfoDto) o;
        return getId().equals(that.getId()) && getName().equals(that.getName()) && getType() == that.getType() && getEquipmentImageUrl().equals(that.getEquipmentImageUrl()) && Objects.equals(getNote(), that.getNote()) && getRentState() == that.getRentState() && Objects.equals(getRecentRenter(), that.getRecentRenter()) && Objects.equals(getRecentRentalEpochSecond(), that.getRecentRentalEpochSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
