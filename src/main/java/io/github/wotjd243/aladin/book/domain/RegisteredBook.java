package io.github.wotjd243.aladin.book.domain;

import io.github.wotjd243.aladin.common.domain.UnitAmount;
import io.github.wotjd243.aladin.enrollment.domain.SellType;
import io.github.wotjd243.aladin.exception.AlreadyReservationException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    @Embedded
    private UnitAmount amount;

    @Enumerated(EnumType.STRING)
    private SellType sellType;

    private boolean reserved;

    private Long enrollmentId;

    @Builder
    public RegisteredBook(Long bookId, UnitAmount unitAmount, SellType sellType, Long enrollmentId) {
        this.bookId = bookId;
        this.amount = unitAmount;
        this.sellType = sellType;
        this.enrollmentId = enrollmentId;
    }

    public void reserve() {

        if (isReserved()) {
            throw new AlreadyReservationException();
        }

        reserved = true;
    }

    public void cancel() {

        if (isCanceled()) {
            throw new AlreadyReservationException("이미 예약 취소 되었습니다.");
        }

        reserved = false;
    }

    public boolean isReserved() {
        return reserved;
    }

    private boolean isCanceled() {
        return !reserved;
    }
}
