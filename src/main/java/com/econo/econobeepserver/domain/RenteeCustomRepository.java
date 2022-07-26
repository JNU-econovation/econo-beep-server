package com.econo.econobeepserver.domain;

import com.econo.econobeepserver.domain.Book.Book;

import java.util.List;

public interface RenteeCustomRepository {

    List<Book> searchRenteeByKeyword(String keyword);
}
