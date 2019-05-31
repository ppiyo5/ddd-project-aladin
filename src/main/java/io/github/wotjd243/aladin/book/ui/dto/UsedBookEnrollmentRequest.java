package io.github.wotjd243.aladin.book.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsedBookEnrollmentRequest {

    @NotNull
    private Long bookId;

    @Min(1000)
    @NotNull
    private Long amount;

    @Builder(builderMethodName = "createBuilder")
    private UsedBookEnrollmentRequest(Long bookId, Long amount) {
        this.bookId = bookId;
        this.amount = amount;
    }


}

