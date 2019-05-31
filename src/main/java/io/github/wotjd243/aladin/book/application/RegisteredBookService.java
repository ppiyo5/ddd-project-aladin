package io.github.wotjd243.aladin.book.application;

import io.github.wotjd243.aladin.book.application.dto.RegisteredBookDto;
import io.github.wotjd243.aladin.book.application.dto.RegisteredBookRequestDto;
import io.github.wotjd243.aladin.book.domain.Book;
import io.github.wotjd243.aladin.book.domain.RegisteredBook;
import io.github.wotjd243.aladin.book.domain.RegisteredBookRepository;
import io.github.wotjd243.aladin.book.infra.RegisteredBookTranslator;
import io.github.wotjd243.aladin.book.infra.dto.RegisteredBookResponse;
import io.github.wotjd243.aladin.enrollment.domain.SellType;
import io.github.wotjd243.aladin.exception.NotFoundException;
import io.github.wotjd243.aladin.exception.WrongValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RegisteredBookService {

    private final BookService bookService;
    private final RegisteredBookRepository registeredBookRepository;

    private final RegisteredBookSessionManager sessionManager;

    public RegisteredBook findById(Long registeredBookId) {

        return registeredBookRepository.findById(registeredBookId)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 책이 존재하지 않습니다.", registeredBookId)));
    }

    public void save(HttpSession session, RegisteredBookRequestDto registeredBookRequestDto) {
        Book book = bookService.findById(registeredBookRequestDto.getBookId());

        if (registeredBookRequestDto.getSellType() == SellType.NEW) {
            registeredBookRequestDto.setAmount(book.getPrice());
            sessionManager.addNewRegisteredBook(session, registeredBookRequestDto);
            return;
        }

        validateAmount(book.getPrice(), registeredBookRequestDto.getAmount());
        sessionManager.addUsedRegisteredBook(session, registeredBookRequestDto);
    }

    private void validateAmount(Long price, Long amountOfUsedBook) {
        if (price < amountOfUsedBook) {
            throw new WrongValueException("원래 책값보다 비쌉니다.");
        }
    }

    public void save(Long enrollmentId, List<RegisteredBookDto> registeredBookDtos) {
        List<RegisteredBook> registeredBooks = registeredBookDtos.stream()
                .map(dto -> convert(enrollmentId, dto))
                .collect(Collectors.toList());

        registeredBookRepository.saveAll(registeredBooks);
    }

    private RegisteredBook convert(Long enrollmentId, RegisteredBookDto dto) {
        return RegisteredBook.builder()
                .bookId(dto.getBookId())
                .unitAmount(dto.getAmount())
                .sellType(dto.getSellType())
                .enrollmentId(enrollmentId)
                .build();
    }

    public List<RegisteredBookResponse> findBy(Pageable convert) {
        List<RegisteredBook> registeredBooks = registeredBookRepository.findAll(convert)
                .getContent();

        return registeredBooks.stream()
                .map(registeredBook -> {
                    String bookName = bookService.findById(registeredBook.getBookId()).getName();
                    return RegisteredBookTranslator.translate(registeredBook, bookName);
                }).collect(Collectors.toList());
    }
}
