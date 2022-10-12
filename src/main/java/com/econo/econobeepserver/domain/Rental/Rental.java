package com.econo.econobeepserver.domain.Rental;

import com.econo.econobeepserver.domain.BaseTimeEntity;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Rental extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentee_id")
    private Rentee rentee;

    @NotNull
    private Long renterId;

    @NotNull
    private String renterName;

    @NotNull
    private LocalDateTime rentalDateTime;

    private LocalDateTime returnDateTime;

    @Builder
    public Rental(Long renterId, String renterName) {
        this.renterId = renterId;
        this.renterName = renterName;
        this.rentalDateTime = LocalDateTime.now();
    }

    public void rentRentee(Rentee rentee) {
        this.rentee = rentee;
    }

    public void returnRentee() {
        returnDateTime = LocalDateTime.now();
    }
}
