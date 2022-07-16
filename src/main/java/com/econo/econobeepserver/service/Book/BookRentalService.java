package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookRentalService {

    private final BookRepository bookRepository;
    private final BookRentalRepository bookRentalRepository;


    @Transactional
    public void rentBookById(Long id) {
    }

    @Transactional
    public void returnBookById(Long id) {
    }
}
