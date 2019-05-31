package io.github.wotjd243.aladin.reservation.application.dto;

import io.github.wotjd243.aladin.book.domain.Book;
import io.github.wotjd243.aladin.book.domain.RegisteredBook;
import io.github.wotjd243.aladin.enrollment.domain.Enrollment;
import io.github.wotjd243.aladin.reservation.domain.Reservations;
import io.github.wotjd243.aladin.reservation.infra.ReservationTranslate;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReservationBookDtoCollection {

    private static final Integer DELIVERY_FEE_MIN_COUNT = 3;
    private static final Integer MANY_BUY_DISCOUNT_COUNT = 5;
    private static final Long DELIVERY_FEE = 2500L;

    private List<ReservationBookDto> reservationBooks;

    ReservationBookDtoCollection(Reservations reservations, List<RegisteredBook> registeredBookList, List<Book> bookList, List<Enrollment> enrollmentList) {
        reservationBooks = changeManyBuy(reservations.getReservations().stream()
                .map( reservation ->
                    ReservationTranslate.translate(reservation, registeredBookList, bookList, enrollmentList)
                )
                .collect(Collectors.toList()));
    }

    public Long computeDeliveryFee() {

        if (CollectionUtils.isEmpty(reservationBooks)) {
            return 0L;
        }

        if (reservationBooks.size() > DELIVERY_FEE_MIN_COUNT) {
            return 0L;
        }

        return DELIVERY_FEE;
    }

    public Long computeTotalAmount() {

        if (CollectionUtils.isEmpty(reservationBooks)) {
            return 0L;
        }

        return reservationBooks.stream()
                .mapToLong(ReservationBookDto::getAmount)
                .sum();
    }

    public Integer computeCount() {

        if (CollectionUtils.isEmpty(reservationBooks)) {
            return 0;
        }

        return reservationBooks.size();
    }

    public List<ReservationBookDto> changeManyBuy(List<ReservationBookDto> reservationBooks) {

        if (CollectionUtils.isEmpty(reservationBooks)) {
            return Collections.emptyList();
        }

        if (reservationBooks.size() < MANY_BUY_DISCOUNT_COUNT) {

            return reservationBooks.stream()
                    .map(ReservationBookDto::changeSmallBuy)
                    .collect(Collectors.toList());
        }

        return reservationBooks
                .stream()
                .map(ReservationBookDto::changeManyBuy)
                .collect(Collectors.toList());
    }

    public Long computeManyBuyDiscountAmount() {

        if (CollectionUtils.isEmpty(reservationBooks)) {
            return 0L;
        }

        if (reservationBooks.size() < MANY_BUY_DISCOUNT_COUNT) {

            reservationBooks
                    .forEach(ReservationBookDto::changeSmallBuy);

            return 0L;
        }

        reservationBooks
                .forEach(ReservationBookDto::changeManyBuy);

        return reservationBooks.stream()
                .mapToLong(ReservationBookDto::getManyBuyDiscountAmount)
                .sum();
    }

    public Long computeEventDiscountAmount() {

        if (CollectionUtils.isEmpty(reservationBooks)) {
            return 0L;
        }

        return reservationBooks.stream()
                .mapToLong(ReservationBookDto::getEventDiscountAmount)
                .sum();
    }

    public Long computeUsedDiscountAmount() {

        if (CollectionUtils.isEmpty(reservationBooks)) {
            return 0L;
        }

        return reservationBooks.stream()
                .mapToLong(ReservationBookDto::getUsedDiscountAmount)
                .sum();
    }
}
