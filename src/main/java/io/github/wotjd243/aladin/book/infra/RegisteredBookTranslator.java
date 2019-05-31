package io.github.wotjd243.aladin.book.infra;

import io.github.wotjd243.aladin.book.domain.RegisteredBook;
import io.github.wotjd243.aladin.book.infra.dto.RegisteredBookResponse;

public class RegisteredBookTranslator {

    public static RegisteredBookResponse translate(RegisteredBook registeredBook, String book) {

        return RegisteredBookResponse.createBuilder()
                .id(registeredBook.getId())
                .amount(registeredBook.getAmount().getAmount())
                .book(book)
                .sellType(registeredBook.getSellType())
                .reserved(registeredBook.isReserved())
                .enrollmentId(registeredBook.getEnrollmentId())
                .build();

    }
}
