package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.Book;
import com.econo.econobeepserver.domain.Book.BookCoverImage;
import com.econo.econobeepserver.domain.Book.BookCoverImageRepository;
import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.RenteeType;
import com.econo.econobeepserver.dto.Book.BookElementDto;
import com.econo.econobeepserver.dto.Book.BookInfoDto;
import com.econo.econobeepserver.dto.Book.BookManagementInfoDto;
import com.econo.econobeepserver.dto.Book.BookSaveDto;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
import com.econo.econobeepserver.service.ImageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookCoverImageRepository bookCoverImageRepository;

    private final ImageHandler imageHandler;

    @Transactional
    public Long createBook(BookSaveDto bookSaveDto) {
        Book book = bookSaveDto.toEntity();
        BookCoverImage bookCoverImage = imageHandler.parseBookCoverImage(bookSaveDto.getBookCoverImage());
        book.setBookCoverImage(bookCoverImage);
        bookCoverImage.setBook(book);

        imageHandler.downloadImage(bookSaveDto.getBookCoverImage(), bookCoverImage.getFilePath());
        long bookId = bookRepository.save(book).getId();
        bookCoverImageRepository.save(bookCoverImage);


        return bookId;
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

    public String getBookCoverImageFilePathByBookId(long bookId) {
        Book book = getBookById(bookId);
        return book.getBookCoverImage().getFilePath();
    }

    @Transactional
    public void updateBookById(Long id, BookSaveDto bookSaveDto) {
        Book book = getBookById(id);
        BookCoverImage oldBookCoverImage = book.getBookCoverImage();
        BookCoverImage newBookCoverImage = imageHandler.parseBookCoverImage(bookSaveDto.getBookCoverImage());

        imageHandler.deleteImage(oldBookCoverImage.getFilePath());
        bookCoverImageRepository.deleteById(oldBookCoverImage.getId());

        book.updateBook(bookSaveDto);
        book.setBookCoverImage(newBookCoverImage);
        newBookCoverImage.setBook(book);

        imageHandler.downloadImage(bookSaveDto.getBookCoverImage(), newBookCoverImage.getFilePath());
        bookCoverImageRepository.save(newBookCoverImage);
    }


    @Transactional
    public void deleteBookById(Long id) {
        try {
            Book book = getBookById(id);
            book.clearAttributes();

            bookRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundRenteeException();
        }
    }
}
