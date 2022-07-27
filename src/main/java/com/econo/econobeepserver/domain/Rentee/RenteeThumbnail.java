package com.econo.econobeepserver.domain.Rentee;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class RenteeThumbnail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(mappedBy = "thumbnail")
    private Rentee rentee;

    @NotNull
    private String filePath;


    @Builder
    public RenteeThumbnail(String filePath, Rentee rentee) {
        this.filePath = filePath;
        this.rentee = rentee;
    }


    public void setRentee(Rentee rentee) {
        this.rentee = rentee;
    }
}
