package com.econo.econobeepserver.dto.Rentee;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Equipment.Equipment;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
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
    private String authorName;
    private String thumbnailUrl;
    private RentState rentState;


    public RenteeElementDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.type = book.getType();
        this.authorName = book.getAuthorName();
        this.thumbnailUrl = "/book/" + book.getId() + "/image";
        this.rentState = book.getRentState();
    }

    public RenteeElementDto(Equipment equipment) {
        this.id = equipment.getId();
        this.title = equipment.getTitle();
        this.type = equipment.getType();
        this.thumbnailUrl = "/equipment/" + equipment.getId() + "/image";
        this.rentState = equipment.getRentState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RenteeElementDto)) return false;
        RenteeElementDto that = (RenteeElementDto) o;
        return id.equals(that.id) && thumbnailUrl.equals(that.thumbnailUrl) && title.equals(that.title) && type == that.type && authorName.equals(that.authorName) && rentState == that.rentState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
