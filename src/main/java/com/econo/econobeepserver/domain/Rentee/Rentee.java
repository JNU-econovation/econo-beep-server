package com.econo.econobeepserver.domain.Rentee;

import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.dto.Rentee.BookSaveDto;
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
public class Rentee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rentee_thumbnail_id")
    private RenteeThumbnail thumbnail;

    @NotNull
    @OneToMany(mappedBy = "rentee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Rental> rentalHistories = new ArrayList<>();

    @NotNull
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RenteeType type;

    private String authorName;

    private String publisherName;

    private LocalDate publishedDate;

    @NotNull
    private RentState rentState = RentState.RENTABLE;

    @NotNull
    private int rentCount = 0;

    private String note;

    @Builder
    public Rentee(Long id, RenteeThumbnail thumbnail, String title, RenteeType type, String authorName, String publisherName, LocalDate publishedDate, String note) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.title = title;
        this.type = type;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishedDate = publishedDate;
        this.note = note;
    }


    public void updateInformation(RenteeSaveDto renteeSaveDto) {
        this.title = renteeSaveDto.getTitle();
        this.type = renteeSaveDto.getType();
        this.authorName = renteeSaveDto.getAuthorName();
        this.publisherName = renteeSaveDto.getPublisherName();
        this.publishedDate = toLocalDate(renteeSaveDto.getPublishedDateEpochSecond());
        this.note = renteeSaveDto.getNote();
    }

    public void rentRentee(Rental rental) {
        rentState = RentState.RENTED;
        rentCount++;

        rentalHistories.add(rental);
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
        rentalHistories.clear();
    }
}
