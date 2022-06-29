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

    @NotNull
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    private String filePath;

    @NotNull
    private Long fileSize;


    @Builder
    public BookCoverImage(String filePath, Long fileSize, Book book) {
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.book = book;
    }
}
