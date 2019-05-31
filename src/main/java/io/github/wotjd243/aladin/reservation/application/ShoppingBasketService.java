package io.github.wotjd243.aladin.reservation.application;

import io.github.wotjd243.aladin.book.application.BookService;
import io.github.wotjd243.aladin.book.application.RegisteredBookService;
import io.github.wotjd243.aladin.book.domain.Book;
import io.github.wotjd243.aladin.book.domain.RegisteredBook;
import io.github.wotjd243.aladin.enrollment.application.EnrollmentService;
import io.github.wotjd243.aladin.enrollment.domain.Enrollment;
import io.github.wotjd243.aladin.person.application.BuyerService;
import io.github.wotjd243.aladin.person.domain.Buyer;
import io.github.wotjd243.aladin.reservation.domain.Reservation;
import io.github.wotjd243.aladin.reservation.domain.ShoppingBasket;
import io.github.wotjd243.aladin.reservation.domain.ShoppingBasketRepository;
import io.github.wotjd243.aladin.reservation.infra.ShoppingBasketTranslate;
import io.github.wotjd243.aladin.reservation.application.dto.ShoppingBasketResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingBasketService {

    private final BuyerService buyerService;
    private final ShoppingBasketRepository shoppingBasketRepository;
    private final RegisteredBookService registeredBookService;
    private final BookService bookService;
    private final EnrollmentService enrollmentService;

    public ShoppingBasket findByBuyerId(String buyerId) {

        Buyer buyer = buyerService.findById(buyerId);

        return shoppingBasketRepository.findById(buyer.getId())
                .orElseGet(() -> shoppingBasketRepository.save(new ShoppingBasket(buyer.getId())));
    }

    public ShoppingBasketResponseDto findShoppingBasket(String buyerId) {

        ShoppingBasket shoppingBasket = findByBuyerId(buyerId);

        List<RegisteredBook> registeredBookList = shoppingBasket.getReservations()
                .getReservations().stream()
                .map(Reservation::getRegisteredBookId)
                .map(registeredBookService::findById)
                .collect(Collectors.toList());

        List<Book> bookList = registeredBookList.stream()
                .map(RegisteredBook::getBookId)
                .map(bookService::findById)
                .collect(Collectors.toList());

        List<Enrollment> enrollmentList = registeredBookList.stream()
                .map(RegisteredBook::getEnrollmentId)
                .map(enrollmentService::findById)
                .collect(Collectors.toList());


        return ShoppingBasketTranslate.translate(shoppingBasket, registeredBookList, bookList, enrollmentList);
    }

}
