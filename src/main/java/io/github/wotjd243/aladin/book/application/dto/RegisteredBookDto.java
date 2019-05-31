package io.github.wotjd243.aladin.book.application.dto;

import io.github.wotjd243.aladin.common.domain.UnitAmount;
import io.github.wotjd243.aladin.enrollment.domain.SellType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBookDto {

    private String sellerId;

    private Long bookId;

    private UnitAmount amount;

    private SellType sellType;

    @Builder
    public RegisteredBookDto(String sellerId, Long bookId, UnitAmount amount, SellType sellType) {
        this.bookId = bookId;
        this.amount = amount;
        this.sellType = sellType;
        this.sellerId = sellerId;
    }
}
