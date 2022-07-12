package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import com.econo.econobeepserver.exception.AlreadyRentedException;
import com.econo.econobeepserver.exception.NotFoundBookException;
import com.econo.econobeepserver.exception.NotRenterException;
import com.econo.econobeepserver.exception.UnrentableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookRentalService {

    private final BookRepository bookRepository;
    private final BookRentalRepository bookRentalRepository;

    @Transactional
    public void rentBookByBookId(Long bookId) throws NotFoundBookException, AlreadyRentedException, UnrentableException {
    }

    @Transactional
    public void returnBookByBookId(Long bookId) throws NotFoundBookException, NotRenterException {
    }
}
