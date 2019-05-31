package io.github.wotjd243.aladin.reservation.infra;

import io.github.wotjd243.aladin.book.domain.Book;
import io.github.wotjd243.aladin.book.domain.RegisteredBook;
import io.github.wotjd243.aladin.enrollment.domain.Enrollment;
import io.github.wotjd243.aladin.reservation.domain.Reservation;
import io.github.wotjd243.aladin.reservation.domain.ShoppingBasket;
import io.github.wotjd243.aladin.reservation.application.dto.ReservationBookDto;
import io.github.wotjd243.aladin.reservation.application.dto.ShoppingBasketResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingBasketTranslate {

    public static ShoppingBasketResponseDto translate(ShoppingBasket shoppingBasket, List<RegisteredBook> registeredBookList, List<Book> bookList, List<Enrollment> enrollmentList) {

        return ShoppingBasketResponseDto.responseBuilder()
                .shoppingBasket(shoppingBasket)
                .registeredBookList(registeredBookList)
                .bookList(bookList)
                .enrollmentList(enrollmentList)
                .build();
    }
//
//    public static ReservationBookDto translate(Reservation reservation) {
//
//        return ReservationBookDto.builder()
//                .bookId(reservation.getRegisteredBookId())
//                .amount(reservation.getAmount())
//                .build();
//    }
}
