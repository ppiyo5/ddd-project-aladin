package io.github.wotjd243.aladin.book.application;

import io.github.wotjd243.aladin.book.domain.Book;
import io.github.wotjd243.aladin.book.domain.BookRepository;
import io.github.wotjd243.aladin.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 책이 존재하지 않습니다.", id)));

    }

    public List<Book> findAll() {

        return repository.findAll();

    }

}
