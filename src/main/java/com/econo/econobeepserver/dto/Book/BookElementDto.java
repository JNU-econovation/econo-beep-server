package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@NoArgsConstructor
public class BookElementDto {

    private Long id;
    private String bookCoverImageUrl;
    private String title;
    private RenteeType type;
    private String authorName;
    private RentState rentState;


    public BookElementDto(Book book) {
        this.id = book.getId();
        // TODO: code to generate BookCoverImageUrl
        this.bookCoverImageUrl = "TODO";
        this.title = book.getTitle();
        this.type = book.getType();
        this.authorName = book.getAuthorName();
        this.rentState = book.getRentState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookElementDto)) return false;
        BookElementDto that = (BookElementDto) o;
        return id.equals(that.id) && bookCoverImageUrl.equals(that.bookCoverImageUrl) && title.equals(that.title) && type == that.type && authorName.equals(that.authorName) && rentState == that.rentState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
