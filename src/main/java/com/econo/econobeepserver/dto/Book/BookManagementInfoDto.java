package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.BookRental.BookRental;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class BookManagementInfoDto {

    private Long id;
    private String bookCoverImageUrl;
    private String title;
    private RenteeType type;
    private String authorName;
    private String publisherName;
    private LocalDate publishedDate;
    private RentState rentState;
    private String recentRenter;
    private Long recentRentalEpochSecond;
    private String note;

    public BookManagementInfoDto(Book book) {
        this.id = book.getId();
        // TODO: code to generate BookCoverImageUrl
        this.bookCoverImageUrl = "TODO";
        this.title = book.getTitle();
        this.type = book.getType();
        this.authorName = book.getAuthorName();
        this.publisherName = book.getPublisherName();
        this.publishedDate = book.getPublishedDate();
        this.rentState = book.getRentState();

        final List<BookRental> bookRentals = book.getRentalHistories();
        if (!bookRentals.isEmpty()) {
            final BookRental recentBookRental = bookRentals.get(bookRentals.size() - 1);
            // TODO: userId가 반환되고 있음. 차후에 userApiServer 연결필요.
            this.recentRenter = recentBookRental.getUserId().toString();
            this.recentRentalEpochSecond = recentBookRental.getRentalDateTime().toEpochSecond(ZoneOffset.of("+09:00"));
        }
        this.note = book.getNote();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookManagementInfoDto)) return false;
        BookManagementInfoDto that = (BookManagementInfoDto) o;
        return getId().equals(that.getId()) && getBookCoverImageUrl().equals(that.getBookCoverImageUrl()) && getTitle().equals(that.getTitle()) && getType() == that.getType() && getAuthorName().equals(that.getAuthorName()) && getPublisherName().equals(that.getPublisherName()) && getPublishedDate().equals(that.getPublishedDate()) && getRentState() == that.getRentState() &&
                Objects.equals(getRecentRenter(), that.getRecentRenter()) &&
                Objects.equals(getRecentRentalEpochSecond(), that.getRecentRentalEpochSecond()) &&
                Objects.equals(getNote(), that.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
