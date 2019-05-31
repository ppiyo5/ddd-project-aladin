package io.github.wotjd243.aladin.book.application.dto;

import io.github.wotjd243.aladin.common.domain.UnitAmount;
import io.github.wotjd243.aladin.enrollment.domain.SellType;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBookRequestDto {

    private String sellerId;

    private Long bookId;

    private Long count;

    @Setter
    private Long amount;

    private SellType sellType;

    @Builder
    public RegisteredBookRequestDto(String sellerId, Long bookId, Long count, Long amount, SellType sellType) {
        this.sellerId = sellerId;
        this.bookId = bookId;
        this.count = count;
        this.amount = amount;
        this.sellType = sellType;
    }

    public RegisteredBookDto toSession() {
        return RegisteredBookDto.builder()
                .bookId(bookId)
                .amount(new UnitAmount(amount))
                .sellType(sellType)
                .sellerId(sellerId)
                .build();

    }

}
