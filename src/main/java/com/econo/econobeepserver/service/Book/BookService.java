package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.Book.BookType;
import com.econo.econobeepserver.dto.Book.BookElementDto;
import com.econo.econobeepserver.dto.Book.BookInfoDto;
import com.econo.econobeepserver.dto.Book.BookManagementInfoDto;
import com.econo.econobeepserver.dto.Book.BookSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public void createBook(BookSaveDto bookSaveDto) {
        bookRepository.save(bookSaveDto.toEntity());
    }


    public Optional<Book> getBookById(Long id) {
        return null;
    }

    public Optional<BookInfoDto> getBookInfoDtoById(Long id) {
        return null;
    }
    
    public List<BookElementDto> getBookElementDtosWithPaging(int pageSize, Long lastId) {
        return null;
    }
    
    public List<BookElementDto> getBookElementDtosByBookTypeWithPaging(BookType bookType, int pageSize, Long lastId) {
        return null;
    }

    public List<BookElementDto> searchBookElementDtosByKeyword(String keyword) {
        return null;
    }

    public List<String> getBookSearchSuggestionsByKeyword(String keyword) {
        return null;
    }

    public List<BookManagementInfoDto> getBookManagementInfoDtosByIdAscWithPaging(int pageSize, Long lastId) {
        return null;
    }

    public List<BookManagementInfoDto> searchBookManagementInfoDtosByKeyword(String keyword) {
        return null;
    }


    @Transactional
    public void updateBookById(Long id, BookSaveDto bookSaveDto) {
    }


    @Transactional
    public void deleteBookById(Long id) {
    }
}
