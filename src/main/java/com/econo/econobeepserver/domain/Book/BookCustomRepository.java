package com.econo.econobeepserver.domain.Book;

import com.econo.econobeepserver.domain.RenteeType;

import java.util.List;

public interface BookCustomRepository {

    public List<Book> getBookWithPaging(int pageSize, Long lastId);

    public List<Book> getBookByTypeWithPaging(RenteeType renteeType, int pageSize, Long lastId);

    public List<Book> searchBookByKeyword(String keyword);

    public List<String> getSearchSuggestionsByKeyword(String keyword);

    public List<Book> getBookByIdDescWithPaging(int pageSize, Long lastId);

}
