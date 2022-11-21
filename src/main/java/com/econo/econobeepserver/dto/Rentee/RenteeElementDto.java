package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Rentee.BookArea;
import com.econo.econobeepserver.domain.Rentee.Rentee;
import com.econo.econobeepserver.domain.Rentee.RentState;
import com.econo.econobeepserver.domain.Rentee.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class RenteeElementDto {

    private Long id;
    private String thumbnailUrl;
    private RenteeType type;
    private String name;
    private BookArea bookArea;
    private String bookAuthorName;
    private RentState rentState;


    public RenteeElementDto(Rentee rentee) {
        this.id = rentee.getId();
        this.thumbnailUrl = "/api/rentees/" + rentee.getId() + "/thumbnail";
        this.type = rentee.getType();
        this.name = rentee.getName();
        this.bookArea = rentee.getBookArea();
        this.bookAuthorName = rentee.getBookAuthorName();
        this.rentState = rentee.getRentState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RenteeElementDto)) return false;
        RenteeElementDto that = (RenteeElementDto) o;
        return getThumbnailUrl().equals(that.getThumbnailUrl()) && getType() == that.getType() && getName().equals(that.getName()) && getBookArea() == that.getBookArea() && Objects.equals(getBookAuthorName(), that.getBookAuthorName()) && getRentState() == that.getRentState();
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
