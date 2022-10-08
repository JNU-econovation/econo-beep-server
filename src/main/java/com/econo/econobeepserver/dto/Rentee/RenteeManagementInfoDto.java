package com.econo.econobeepserver.dto.Rentee;

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
    private String title;
    private RenteeType type;
    private String bookAuthorName;
    private String bookPublisherName;
    private Long bookPublishedDateEpochSecond;
    private String thumbnailUrl;
    private String note;
    private RentState rentState;

    private String recentRenter;
    private Long recentRentalEpochSecond;

    public RenteeManagementInfoDto(Rentee rentee) {
        this.id = rentee.getId();
        this.title = rentee.getName();
        this.type = rentee.getType();
        this.bookAuthorName = rentee.getBookAuthorName();
        this.bookPublisherName = rentee.getBookPublisherName();
        this.bookPublishedDateEpochSecond = toEpochSecond(rentee.getBookPublishedDate());
        this.thumbnailUrl = "/rentee/" + rentee.getId() + "/thumbnail";
        this.note = rentee.getNote();
        this.rentState = rentee.getRentState();

        final List<Rental> rentals = rentee.getRentals();
        if (!rentals.isEmpty()) {
            final Rental recentRental = rentals.get(rentals.size() - 1);

            this.recentRenter = recentRental.getRenterName();
            this.recentRentalEpochSecond = toEpochSecond(recentRental.getRentalDateTime());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RenteeManagementInfoDto)) return false;
        RenteeManagementInfoDto that = (RenteeManagementInfoDto) o;
        return getId().equals(that.getId()) && getTitle().equals(that.getTitle()) && getType() == that.getType() && Objects.equals(getAuthorName(), that.getAuthorName()) && Objects.equals(getPublisherName(), that.getPublisherName()) && Objects.equals(getPublishedDateEpochSecond(), that.getPublishedDateEpochSecond()) && Objects.equals(getThumbnailUrl(), that.getThumbnailUrl()) && Objects.equals(getNote(), that.getNote()) && getRentState() == that.getRentState() && Objects.equals(getRecentRenter(), that.getRecentRenter()) && Objects.equals(getRecentRentalEpochSecond(), that.getRecentRentalEpochSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
