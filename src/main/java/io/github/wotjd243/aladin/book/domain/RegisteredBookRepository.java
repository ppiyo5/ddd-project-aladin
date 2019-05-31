package io.github.wotjd243.aladin.book.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredBookRepository extends JpaRepository<RegisteredBook, Long> {
    Page<RegisteredBook> findAll(Pageable request);

}
