package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rentee.BookArea;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.econo.econobeepserver.util.EpochTime.toEpochSecond;

@Getter
@Setter
@NoArgsConstructor
public class RenteeInfoDto {

    private Long id;
    private RenteeType type;
    private String thumbnailUrl;
    private String name;
    private BookArea bookArea;
    private String bookAuthorName;
    private String bookPublisherName;
    private Long bookPublishedDateEpochSecond;
    private RentState rentState;
    private int rentCount;
    private String note;
    private Boolean isBookmarked;
    private Integer bookmarkCount;
    private List<RentalElementDto> rentalHistories;


    public RenteeInfoDto(Rentee rentee, List<RentalElementDto> rentalElementDtos, boolean isBookmarked, int bookmarkCount) {
        this.id = rentee.getId();
        this.type = rentee.getType();
        this.thumbnailUrl = "/api/rentees/" + rentee.getId() + "/thumbnail";
        this.name = rentee.getName();
        this.bookArea = rentee.getBookArea();
        this.bookAuthorName = rentee.getBookAuthorName();
        this.bookPublisherName = rentee.getBookPublisherName();
        this.bookPublishedDateEpochSecond = toEpochSecond(rentee.getBookPublishedDate());
        this.rentState = rentee.getRentState();
        this.rentCount = rentee.getRentCount();
        this.note = rentee.getNote();
        this.isBookmarked = isBookmarked;
        this.bookmarkCount = bookmarkCount;
        this.rentalHistories = rentalElementDtos;
    }
}
