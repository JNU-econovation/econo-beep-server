package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.dto.Book.BookElementDto;
import com.econo.econobeepserver.dto.Book.BookInfoDto;
import com.econo.econobeepserver.dto.Book.BookManagementInfoDto;
import com.econo.econobeepserver.dto.Book.BookSaveDto;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    @Transactional
    public void createBook(BookSaveDto bookSaveDto) {
        bookRepository.save(bookSaveDto.toEntity());
    }


    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundRenteeException();
        }

        return book.get();
    }

    public BookInfoDto getBookInfoDtoById(Long id) {
        Book book = getBookById(id);

        return new BookInfoDto(book);
    }
    
    public List<BookElementDto> getBookElementDtosWithPaging(int pageSize, Long lastId) {
        List<Book> books = bookRepository.getRecentBookWithPaging(pageSize, lastId);

        return books.stream().map(BookElementDto::new).collect(Collectors.toList());
    }

    public List<BookElementDto> getBookElementDtosByBookTypeWithPaging(RenteeType bookType, int pageSize, Long lastId) {
        List<Book> books = bookRepository.getBookByTypeWithPaging(bookType, pageSize, lastId);

        return books.stream().map(BookElementDto::new).collect(Collectors.toList());
    }

    public List<BookElementDto> searchBookElementDtosByKeyword(String keyword) {
        List<Book> books = bookRepository.searchBookByKeyword(keyword);

        return books.stream().map(BookElementDto::new).collect(Collectors.toList());
    }

    public List<BookManagementInfoDto> getBookManagementInfoDtosByIdDescWithPaging(int pageSize, Long lastId) {
        List<Book> books = bookRepository.getRecentBookWithPaging(pageSize, lastId);

        return books.stream().map(BookManagementInfoDto::new).collect(Collectors.toList());
    }

    public List<BookManagementInfoDto> searchBookManagementInfoDtosByKeyword(String keyword) {
        List<Book> books = bookRepository.searchBookByKeyword(keyword);

        return books.stream().map(BookManagementInfoDto::new).collect(Collectors.toList());
    }


    @Transactional
    public void updateBookById(Long id, BookSaveDto bookSaveDto) {
        Book book = getBookById(id);

        book.updateBook(bookSaveDto);
    }


    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
