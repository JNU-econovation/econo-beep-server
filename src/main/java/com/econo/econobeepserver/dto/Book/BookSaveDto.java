package com.econo.econobeepserver.dto.Book;

import com.econo.econobeepserver.domain.Book.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSaveDto {

    public Book toEntity() {
        return null;
    }

    public BookSaveDto(Book book) {
    }
}
