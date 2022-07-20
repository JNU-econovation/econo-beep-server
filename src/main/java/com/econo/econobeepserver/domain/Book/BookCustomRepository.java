package com.econo.econobeepserver.domain.Book;

import com.econo.econobeepserver.domain.RenteeType;

import java.util.List;

public interface BookCustomRepository {

    List<Book> getRecentBookWithPaging(int pageSize, Long lastId);

    List<Book> getBookByTypeWithPaging(RenteeType renteeType, int pageSize, Long lastId);

    List<Book> searchBookByKeyword(String keyword);

    List<String> getSearchSuggestionsByKeyword(String keyword);
}
