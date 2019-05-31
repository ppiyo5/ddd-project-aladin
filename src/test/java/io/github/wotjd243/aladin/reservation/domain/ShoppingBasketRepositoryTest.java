package io.github.wotjd243.aladin.reservation.domain;

import io.github.wotjd243.aladin.common.domain.UnitAmount;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingBasketRepositoryTest {

    @Autowired
    private ShoppingBasketRepository shoppingBasketRepository;

    @After
    public void tearDown() throws Exception {
        shoppingBasketRepository.deleteAll();
    }

    @Test
    public void 저장테스트() {

        //given
        String buyerId = "1";
        Reservations reservations = new Reservations(Arrays.asList(
                new Reservation(1L, LocalDate.of(2019, 5, 14)),
                new Reservation(2L, LocalDate.of(2019, 5, 14))
        ));

        ShoppingBasket shoppingBasket = new ShoppingBasket(buyerId, reservations);
        //when
        shoppingBasketRepository.save(shoppingBasket);

        //then
        ShoppingBasket result = shoppingBasketRepository.findById(buyerId)
                .get();

        assertThat(result.getBuyerId()).isEqualTo("1");
        Reservations resultReservations = result.getReservations();
        assertThat(resultReservations.getReservations().size()).isEqualTo(2L);
    }

    @Test
    public void 삭제테스트() {

        //given
        String buyerId = "1";
        Reservations reservations = new Reservations(Arrays.asList(
                new Reservation(1L, LocalDate.of(2019, 5, 14)),
                new Reservation(2L, LocalDate.of(2019, 5, 14))
        ));

        ShoppingBasket shoppingBasket = new ShoppingBasket(buyerId, reservations);
        //when
        shoppingBasketRepository.save(shoppingBasket);
        shoppingBasket.removeReservation(reservations.getReservations().get(1).getRegisteredBookId());
        shoppingBasketRepository.save(shoppingBasket);
        //then
        ShoppingBasket result = shoppingBasketRepository.findById(buyerId)
                .get();

        assertThat(result.getBuyerId()).isEqualTo("1");
        Reservations resultReservations = result.getReservations();
        assertThat(resultReservations.getReservations().size()).isEqualTo(1L);
    }

    @Test
    public void 등록테스트() {

        //given
        String buyerId = "1";
        Reservations reservations = new Reservations(Arrays.asList(
                new Reservation(1L, LocalDate.of(2019, 5, 14)),
                new Reservation(2L, LocalDate.of(2019, 5, 14))
        ));

        ShoppingBasket shoppingBasket = new ShoppingBasket(buyerId, reservations);
        //when
        shoppingBasketRepository.save(shoppingBasket);
        shoppingBasket.addReservation(new Reservation(3L, LocalDate.of(2019, 5, 14)));
        shoppingBasketRepository.save(shoppingBasket);
        //then
        ShoppingBasket result = shoppingBasketRepository.findById(buyerId)
                .get();

        assertThat(result.getBuyerId()).isEqualTo("1");
        Reservations resultReservations = result.getReservations();
        assertThat(resultReservations.getReservations().size()).isEqualTo(3L);
    }
}
