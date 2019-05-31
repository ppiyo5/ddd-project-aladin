package io.github.wotjd243.aladin.book.ui;

import io.github.wotjd243.aladin.book.application.BookService;
import io.github.wotjd243.aladin.book.infra.BookTranslator;
import io.github.wotjd243.aladin.book.ui.dto.BookResponseDto;
import io.github.wotjd243.aladin.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    final private BookService bookService;

    @GetMapping(value = "/books/{id}")
    public ApiResponse<BookResponseDto> getBook(@PathVariable Long id) {

        return ApiResponse.createOK(BookTranslator.translate(bookService.findById(id)));
    }

    @GetMapping(value = "/books")
    public ApiResponse<List<BookResponseDto>> getAll() {
        List<BookResponseDto> responseDtoList = bookService.findAll().stream()
                .map(BookTranslator::translate)
                .collect(Collectors.toList());

        return ApiResponse.createOK(responseDtoList);
    }

}
