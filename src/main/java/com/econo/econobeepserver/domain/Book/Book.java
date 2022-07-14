package com.econo.econobeepserver.domain.Book;

import com.econo.econobeepserver.domain.BookRental.BookRental;
import com.econo.econobeepserver.domain.RentState;
import com.econo.econobeepserver.domain.RenteeType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_cover_image_id")
    private BookCoverImage bookCoverImage;

    @NotNull
    @OneToMany(mappedBy = "book")
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
    public Book(BookCoverImage bookCoverImage, String title, RenteeType type, String authorName, String publisherName, LocalDate publishedDate, String note) {
        this.bookCoverImage = bookCoverImage;
        this.title = title;
        this.type = type;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishedDate = publishedDate;
        this.note = note;
    }
}
