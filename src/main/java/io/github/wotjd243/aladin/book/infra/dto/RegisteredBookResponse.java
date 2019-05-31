package io.github.wotjd243.aladin.book.infra.dto;

import io.github.wotjd243.aladin.enrollment.domain.SellType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisteredBookResponse {
    private Long id;

    private String book;

    private Long amount;

    private SellType sellType;

    private boolean reserved;

    private Long enrollmentId;

    @Builder(builderMethodName = "createBuilder")
    private RegisteredBookResponse(Long id, String book, Long amount, SellType sellType, boolean reserved, Long enrollmentId) {
        this.id = id;
        this.book = book;
        this.amount = amount;
        this.sellType = sellType;
        this.reserved = reserved;
        this.enrollmentId = enrollmentId;
    }
}
