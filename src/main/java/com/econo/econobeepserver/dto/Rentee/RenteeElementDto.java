package com.econo.econobeepserver.dto.Rentee;

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
    private String title;
    private RenteeType type;
    private String bookAuthorName;
    private String thumbnailUrl;
    private RentState rentState;


    public RenteeElementDto(Rentee rentee) {
        this.id = rentee.getId();
        this.title = rentee.getName();
        this.type = rentee.getType();
        this.bookAuthorName = rentee.getBookAuthorName();
        this.thumbnailUrl = "/rentee/" + rentee.getId() + "/thumbnail";
        this.rentState = rentee.getRentState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RenteeElementDto)) return false;
        RenteeElementDto that = (RenteeElementDto) o;
        return getId().equals(that.getId()) && getTitle().equals(that.getTitle()) && getType() == that.getType() && Objects.equals(getAuthorName(), that.getAuthorName()) && getThumbnailUrl().equals(that.getThumbnailUrl()) && getRentState() == that.getRentState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
