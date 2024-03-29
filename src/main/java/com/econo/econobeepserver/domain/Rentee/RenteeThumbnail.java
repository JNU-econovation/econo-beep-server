package com.econo.econobeepserver.domain.Rentee;

import com.econo.econobeepserver.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class RenteeThumbnail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String filePath;


    @Builder
    public RenteeThumbnail(String filePath) {
        this.filePath = filePath;
    }
}
