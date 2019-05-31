package io.github.wotjd243.aladin.book.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookResponseDto {

    private Long id;

    private String name;

    private String author;

    private String category;

    private String publisher;

    private Long price;

    @Builder(builderMethodName = "responseBuilder")
    public BookResponseDto(Long id, String name, String author, String category, String publisher, Long price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.price = price;
    }
}
