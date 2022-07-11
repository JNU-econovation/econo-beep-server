package com.econo.econobeepserver.service.Book;

import com.econo.econobeepserver.domain.Book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
}
