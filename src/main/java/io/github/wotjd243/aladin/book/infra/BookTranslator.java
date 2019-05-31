package io.github.wotjd243.aladin.book.infra;

import io.github.wotjd243.aladin.book.domain.Book;
import io.github.wotjd243.aladin.book.domain.Category;
import io.github.wotjd243.aladin.book.infra.dto.BookApiResponseDto;
import io.github.wotjd243.aladin.book.ui.dto.BookResponseDto;

public class BookTranslator {

    public static Book translate(BookApiResponseDto.Item item, String category) {

        return Book.createBuilder()
                .name(item.getOriginTitle())
                .author(item.getAuthor())
                .category(category)
                .publisher(item.getPublisher())
                .price(item.getPrice())
                .build();

    }

    public static BookResponseDto translate(Book book) {

        return BookResponseDto.responseBuilder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .category(book.getCategory())
                .publisher(book.getPublisher())
                .price(book.getPrice())
                .build();
    }
}
