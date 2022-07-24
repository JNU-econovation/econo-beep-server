package com.econo.econobeepserver.domain.Book;

import com.econo.econobeepserver.domain.BookRental.BookRental;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.dto.Book.BookSaveDto;
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
@SequenceGenerator(name = "RENTEE_SEQ_GENERATOR", sequenceName = "RENTEE_SEQ")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RENTEE_SEQ_GENERATOR")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "book_cover_image_id")
    private BookCoverImage bookCoverImage;

    @NotNull
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookRental> rentalHistories = new ArrayList<>();

    @NotNull
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RenteeType type;

    @NotNull
    private String authorName;

    @NotNull
    private String publisherName;

    @NotNull
    private LocalDate publishedDate;

    @NotNull
    private RentState rentState = RentState.RENTABLE;

    @NotNull
    private int rentCount = 0;

    private String note;

    @Builder
    public Book(Long id, BookCoverImage bookCoverImage, String title, RenteeType type, String authorName, String publisherName, LocalDate publishedDate, String note) {
        this.id = id;
        this.bookCoverImage = bookCoverImage;
        this.title = title;
        this.type = type;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishedDate = publishedDate;
        this.note = note;
    }


    public void updateBook(BookSaveDto bookSaveDto) {
        this.title = bookSaveDto.getTitle();
        this.type = bookSaveDto.getType();
        this.authorName = bookSaveDto.getAuthorName();
        this.publisherName = bookSaveDto.getPublisherName();
        this.publishedDate = toLocalDate(bookSaveDto.getPublishedDateEpochSecond());
        this.note = bookSaveDto.getNote();
    }

    public void rentBook(BookRental bookRental) {
        rentState = RentState.RENTED;
        rentCount++;

        rentalHistories.add(bookRental);
        bookRental.setBook(this);
    }

    public void returnBook() {
        this.rentState = RentState.RENTABLE;
    }

    public void disableBook() {
        this.rentState = RentState.UNRENTABLE;
    }

    public void setBookCoverImage(BookCoverImage bookCoverImage) {
        this.bookCoverImage = bookCoverImage;
    }

    public void clearAttributes() {
        bookCoverImage = null;
        rentalHistories.clear();
    }
}
