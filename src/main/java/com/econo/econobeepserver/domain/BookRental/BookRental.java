package com.econo.econobeepserver.domain.BookRental;

import com.econo.econobeepserver.domain.Book.Book;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class BookRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    private Long renterId;

    @NotNull
    private String renterName;

    @NotNull
    private LocalDateTime rentalDateTime;

    private LocalDateTime returnDateTime;

    @Builder
    public BookRental(Long renterId, String renterName) {
        this.renterId = renterId;
        this.renterName = renterName;
        this.rentalDateTime = LocalDateTime.now();
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void returnBook() {
        returnDateTime = LocalDateTime.now();
    }
}
