package io.github.wotjd243.aladin.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByCategory(Category category);

    List<Book> findBooksByNameNameContaining(String name);

}
