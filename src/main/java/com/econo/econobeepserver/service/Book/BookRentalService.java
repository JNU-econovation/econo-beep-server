package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookRentalService {

    private final BookRentalRepository bookRentalRepository;
    private final BookService bookService;


    @Transactional
    public void rentBookById(Long id, String pinCode) {
    }

    @Transactional
    public void returnBookById(Long id, String pinCode) {
    }
}
