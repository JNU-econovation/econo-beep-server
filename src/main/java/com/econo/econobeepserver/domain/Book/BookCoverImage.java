package com.econo.econobeepserver.domain.Book;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class BookCoverImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "book_cover_image")
    private Book book;

    @NotNull
    private String filePath;


    @Builder
    public BookCoverImage(String filePath, Book book) {
        this.filePath = filePath;
        this.book = book;
    }
}
