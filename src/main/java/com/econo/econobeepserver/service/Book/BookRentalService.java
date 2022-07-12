package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import com.econo.econobeepserver.exception.AlreadyRentedException;
import com.econo.econobeepserver.exception.NotFoundRenteeException;
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
    public void rentBookById(Long id) throws NotFoundRenteeException, AlreadyRentedException, UnrentableException {
    }

    @Transactional
    public void returnBookById(Long id) throws NotFoundRenteeException, NotRenterException {
    }
}
