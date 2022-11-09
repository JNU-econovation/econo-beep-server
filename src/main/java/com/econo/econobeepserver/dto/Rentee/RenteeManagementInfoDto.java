package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rentee.BookArea;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rental.Rental;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Setter
@Getter
@NoArgsConstructor
public class RenteeManagementInfoDto {

    private Long id;
    private String thumbnailUrl;
    private RenteeType type;
    private String name;
    private BookArea bookArea;
    private String bookAuthorName;
    private String bookPublisherName;
    private Long bookPublishedDateEpochSecond;
    private String note;
    private RentState rentState;

    private String recentRenter;
    private Long recentRentalEpochSecond;

    public RenteeManagementInfoDto(Rentee rentee, Rental recentRental) {
        this.id = rentee.getId();
        this.thumbnailUrl = "/rentee/" + rentee.getId() + "/thumbnail";
        this.type = rentee.getType();
        this.name = rentee.getName();
        this.bookArea = rentee.getBookArea();
        this.bookAuthorName = rentee.getBookAuthorName();
        this.bookPublisherName = rentee.getBookPublisherName();
        this.bookPublishedDateEpochSecond = toEpochSecond(rentee.getBookPublishedDate());
        this.note = rentee.getNote();
        this.rentState = rentee.getRentState();

        if (recentRental != null) {
            this.recentRenter = recentRental.getRenterName();
            this.recentRentalEpochSecond = toEpochSecond(recentRental.getRentalDateTime());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RenteeManagementInfoDto)) return false;
        RenteeManagementInfoDto that = (RenteeManagementInfoDto) o;
        return getThumbnailUrl().equals(that.getThumbnailUrl()) && getType() == that.getType() && getName().equals(that.getName()) && getBookArea() == that.getBookArea() && Objects.equals(getBookAuthorName(), that.getBookAuthorName()) && Objects.equals(getBookPublisherName(), that.getBookPublisherName()) && Objects.equals(getBookPublishedDateEpochSecond(), that.getBookPublishedDateEpochSecond()) && Objects.equals(getNote(), that.getNote()) && getRentState() == that.getRentState();
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
