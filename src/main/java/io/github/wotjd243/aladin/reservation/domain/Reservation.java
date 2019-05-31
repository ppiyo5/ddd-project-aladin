package io.github.wotjd243.aladin.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Reservation {

    private Long registeredBookId;

    private LocalDate reservationDate;

    public Reservation(Long registeredBookId, LocalDate reservationDate) {
        this.registeredBookId = registeredBookId;
        this.reservationDate = reservationDate;
    }
}
