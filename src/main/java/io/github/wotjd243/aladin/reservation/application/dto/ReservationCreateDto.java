package io.github.wotjd243.aladin.reservation.application.dto;

import io.github.wotjd243.aladin.utils.LongConvert;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReservationCreateDto {

    @Setter(AccessLevel.PRIVATE)
    @NotBlank
    private String bookId;

    public Long getRegisteredBookId() {

        return LongConvert.valueOf("bookId", bookId);
    }
}
