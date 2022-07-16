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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    private Long userId;

    @NotNull
    private LocalDateTime rentalDateTime;

    private LocalDateTime returnDateTime;

    @Builder
    public BookRental(Book book, Long userId) {
        this.book = book;
        this.userId = userId;
        this.rentalDateTime = LocalDateTime.now();
    }
}
