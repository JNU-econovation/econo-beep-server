package com.econo.econobeepserver.domain.Book;

import com.econo.econobeepserver.domain.BookRental.BookRental;
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

    @NotNull
    @OneToOne(mappedBy = "book")
    private BookCoverImage bookCoverImage;

    @NotNull
    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<BookRental> rentalHistories = new ArrayList<>();

    @NotNull
    private String title;

    @NotNull
    private BookType type;

    @NotNull
    private String authorName;

    @NotNull
    private String publisherName;

    @NotNull
    private LocalDate publishedDate;

    @NotNull
    private boolean isRented = false;

    @NotNull
    private int rentCount = 0;

    @Builder
    public Book(BookCoverImage bookCoverImage, String title, BookType type, String authorName, String publisherName, LocalDate publishedDate) {
        this.bookCoverImage = bookCoverImage;
        this.title = title;
        this.type = type;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.publishedDate = publishedDate;
    }
}
