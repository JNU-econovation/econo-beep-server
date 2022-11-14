package com.econo.econobeepserver.domain.Rental;

import com.econo.econobeepserver.domain.BaseTimeEntity;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.User.User;
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
    @JoinColumn(name = "rentee_id", nullable = false)
    private Rentee rentee;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User renter;

    @NotNull
    private LocalDateTime rentalDateTime;

    private LocalDateTime returnDateTime;

    @Builder
    public Rental(Rentee rentee, User renter) {
        this.rentee = rentee;
        this.rentee.rentRentee();

        this.renter = renter;
        this.rentalDateTime = LocalDateTime.now();
    }

    public void returnRentee() {
        returnDateTime = LocalDateTime.now();
        rentee.returnRentee();
    }
}
