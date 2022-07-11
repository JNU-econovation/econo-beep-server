package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.BookRepository;
import com.econo.econobeepserver.domain.BookRental.BookRentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRentalService {

    private final BookRepository bookRepository;
    private final BookRentalRepository bookRentalRepository;
}
