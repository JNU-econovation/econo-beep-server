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
    private Long userId;

    @NotNull
    private LocalDateTime rentalDateTime;

    private LocalDateTime returnDateTime;

    @Builder
    public EquipmentRental(Equipment equipment, Long userId) {
        this.equipment = equipment;
        this.userId = userId;
        this.rentalDateTime = LocalDateTime.now();
    }
}
