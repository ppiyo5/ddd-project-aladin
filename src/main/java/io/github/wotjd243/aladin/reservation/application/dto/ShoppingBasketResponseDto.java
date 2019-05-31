package io.github.wotjd243.aladin.reservation.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.wotjd243.aladin.book.domain.Book;
import io.github.wotjd243.aladin.book.domain.RegisteredBook;
import io.github.wotjd243.aladin.enrollment.domain.Enrollment;
import io.github.wotjd243.aladin.reservation.domain.ShoppingBasket;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ShoppingBasketResponseDto {

    @JsonIgnore
    private final ReservationBookDtoCollection reservationBooks;

    @Builder(builderMethodName = "responseBuilder")
    private ShoppingBasketResponseDto(ShoppingBasket shoppingBasket, List<RegisteredBook> registeredBookList, List<Book> bookList, List<Enrollment> enrollmentList) {
        this.reservationBooks = new ReservationBookDtoCollection(shoppingBasket.getReservations(), registeredBookList, bookList, enrollmentList);
    }

    public List<ReservationBookDto> getBooks() {

        return reservationBooks.getReservationBooks();
    }

    public Long getTotalAmount() {

        return reservationBooks.computeTotalAmount();
    }

    public Long getDeliveryFee() {

        return reservationBooks.computeDeliveryFee();
    }

    public Long getManyBuyDiscountAmount() {

        return reservationBooks.computeManyBuyDiscountAmount();
    }

    public Long getUsedDiscountAmount() {

        return reservationBooks.computeUsedDiscountAmount();
    }

    public Long getEventDiscountAmount() {

        return reservationBooks.computeEventDiscountAmount();
    }

    public Integer getCount() {

        return reservationBooks.computeCount();
    }
}
