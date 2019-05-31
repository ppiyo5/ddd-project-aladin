package io.github.wotjd243.aladin.reservation.infra;

import io.github.wotjd243.aladin.book.domain.Book;
import io.github.wotjd243.aladin.book.domain.RegisteredBook;
import io.github.wotjd243.aladin.enrollment.domain.Enrollment;
import io.github.wotjd243.aladin.enrollment.domain.Event;
import io.github.wotjd243.aladin.exception.NotFoundException;
import io.github.wotjd243.aladin.reservation.application.dto.ReservationBookDto;
import io.github.wotjd243.aladin.reservation.domain.Reservation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationTranslate {

    public static ReservationBookDto translate(Reservation reservation, List<RegisteredBook> registeredBookList, List<Book> bookList, List<Enrollment> enrollmentList) {

        RegisteredBook registeredBook = findByRegisteredBook(registeredBookList, reservation.getRegisteredBookId());
        Book book = findByBook(bookList, registeredBook.getBookId());
        Enrollment enrollment = findByEnrollment(enrollmentList, registeredBook.getEnrollmentId());

        return ReservationBookDto.builder()
                .registeredBookId(registeredBook.getId())
                .bookId(book.getId())
                .bookName(book.getName())
                .amount(book.getPrice())
                .usedDiscountAmount(registeredBook.getAmount().getAmount() - book.getPrice())
                .eventDiscountPercent(enrollment.getEvent(reservation.getReservationDate())
                        .map(Event::getPeriodPercent)
                        .orElse(0.0))
                .build();
    }

    private static RegisteredBook findByRegisteredBook(List<RegisteredBook> registeredBookList, Long registeredBookId) {

        return registeredBookList.stream()
                .filter(registeredBook -> registeredBook.getId().equals(registeredBookId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 책이 존재하지 않습니다.", registeredBookId)));
    }

    private static Book findByBook(List<Book> bookList, Long bookId) {

        return bookList.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 책이 존재하지 않습니다.", bookId)));
    }

    private static Enrollment findByEnrollment(List<Enrollment> enrollmentList, Long enrollmentId) {

        return enrollmentList.stream()
                .filter(enrollment -> enrollment.getId().equals(enrollmentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 등록이 존재하지 않습니다.", enrollmentId)));
    }
}
