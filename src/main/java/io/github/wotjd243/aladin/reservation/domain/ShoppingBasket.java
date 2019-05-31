package io.github.wotjd243.aladin.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collections;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingBasket {

    @Id
    private String buyerId;

    @Embedded
    private Reservations reservations;

    public ShoppingBasket(String buyerId) {
        this.buyerId = buyerId;
        this.reservations = new Reservations(Collections.emptyList());
    }

    public ShoppingBasket(String buyerId, Reservations reservations) {
        this.buyerId = buyerId;
        this.reservations = reservations;
    }

    public void removeReservation(Long registeredBookId) {

        reservations.remove(registeredBookId);
    }

    public void addReservation(Reservation reservation) {

        reservations.add(reservation);
    }

    public boolean exists(Long registeredBookId) {
        return reservations.exists(registeredBookId);
    }
}
