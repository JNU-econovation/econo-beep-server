package com.econo.econobeepserver.domain.EquipmentRental;

import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class EquipmentRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @NotNull
    private Long renterId;

    @NotNull
    private String renterName;

    @NotNull
    private LocalDateTime rentalDateTime;

    private LocalDateTime returnDateTime;

    @Builder
    public EquipmentRental(Equipment equipment, Long renterId, String renterName) {
        this.equipment = equipment;
        this.renterId = renterId;
        this.renterName = renterName;
        this.rentalDateTime = LocalDateTime.now();
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void returnEquipment() {
        rentalDateTime = LocalDateTime.now();
    }
}
