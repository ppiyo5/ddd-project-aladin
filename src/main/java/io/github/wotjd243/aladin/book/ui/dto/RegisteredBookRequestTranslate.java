package io.github.wotjd243.aladin.book.ui.dto;

import io.github.wotjd243.aladin.book.application.dto.RegisteredBookRequestDto;
import io.github.wotjd243.aladin.enrollment.domain.SellType;

public class RegisteredBookRequestTranslate {

    public static RegisteredBookRequestDto translate(String sellerId, NewBookEnrollmentRequest request) {
        return RegisteredBookRequestDto.builder()
                .sellerId(sellerId)
                .bookId(request.getBookId())
                .count(request.getCount())
                .sellType(SellType.NEW)
                .build();
    }

    public static RegisteredBookRequestDto translate(String sellerId, UsedBookEnrollmentRequest request) {
        return RegisteredBookRequestDto.builder()
                .sellerId(sellerId)
                .bookId(request.getBookId())
                .amount(request.getAmount())
                .sellType(SellType.USED)
                .build();
    }
}
