package com.econo.econobeepserver.domain.Rentee;

import com.econo.econobeepserver.domain.BaseTimeEntity;
import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.dto.Rentee.RenteeSaveDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.econo.econobeepserver.util.EpochTime.toLocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Rentee extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rentee_thumbnail_id")
    private RenteeThumbnail thumbnail;

    @NotNull
    @OneToMany(mappedBy = "rentee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Rental> rentals = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private RenteeType type;

    @NotNull
    private String name;

    private BookArea bookArea;

    private String bookAuthorName;

    private String bookPublisherName;

    private LocalDate bookPublishedDate;

    private String note;

    @NotNull
    private RentState rentState = RentState.RENTABLE;

    @NotNull
    private int rentCount = 0;


    @Builder
    public Rentee(Long id, RenteeThumbnail thumbnail, String name, RenteeType type,
                  BookArea bookArea, String bookAuthorName, String bookPublisherName, LocalDate bookPublishedDate, String note) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.type = type;
        this.name = name;
        this.bookArea = bookArea;
        this.bookAuthorName = bookAuthorName;
        this.bookPublisherName = bookPublisherName;
        this.bookPublishedDate = bookPublishedDate;
        this.note = note;
    }


    public void updateInformation(RenteeSaveDto renteeSaveDto) {
        this.type = renteeSaveDto.getType();
        this.name = renteeSaveDto.getName();
        this.bookAuthorName = renteeSaveDto.getBookAuthorName();
        this.bookPublisherName = renteeSaveDto.getBookPublisherName();
        this.bookPublishedDate = toLocalDate(renteeSaveDto.getBookPublishedDateEpochSecond());
        this.note = renteeSaveDto.getNote();
    }

    public void rentRentee(Rental rental) {
        rentState = RentState.RENTED;
        rentCount++;

        rentals.add(rental);
        rental.rentRentee(this);
    }

    public void returnRentee() {
        this.rentState = RentState.RENTABLE;
    }

    public void disableRentee() {
        this.rentState = RentState.UNRENTABLE;
    }

    public void setRenteeThumbnail(RenteeThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void clearAttributes() {
        thumbnail = null;
        rentals.clear();
    }
}
