package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Getter
@Setter
@NoArgsConstructor
public class RenteeInfoDto {

    private Long id;
    private String thumbnailUrl;
    private List<RenteeRentalElementDto> rentalHistories;
    private String title;
    private RenteeType type;
    private String bookAuthorName;
    private String bookPublisherName;
    private Long bookPublishedDateEpochSecond;
    private RentState rentState;
    private int rentCount;
    private String note;


    public RenteeInfoDto(Rentee rentee) {
        this.id = rentee.getId();
        this.thumbnailUrl = "/rentee/" + rentee.getId() + "/thumbnail";
        this.rentalHistories = rentee.getRentals().stream().map(RenteeRentalElementDto::new).collect(Collectors.toList());
        this.title = rentee.getName();
        this.type = rentee.getType();
        this.bookAuthorName = rentee.getBookAuthorName();
        this.bookPublisherName = rentee.getBookPublisherName();
        this.bookPublishedDateEpochSecond = toEpochSecond(rentee.getBookPublishedDate());
        this.rentState = rentee.getRentState();
        this.rentCount = rentee.getRentCount();
        this.note = rentee.getNote();
    }
}
