package io.github.wotjd243.aladin.enrollment.domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    @Test
    public void isEventDate() {
        //given
        Event event = new Event(LocalDate.of(2019, 5, 7), LocalDate.of(2019, 5, 8), 20.0);
        //when
        //then
        assertThat(event.isEventDate(LocalDate.of(2019, 5, 7))).isTrue();
        assertThat(event.isEventDate(LocalDate.of(2019, 5, 8))).isTrue();
        assertThat(event.isEventDate(LocalDate.of(2019, 5, 9))).isFalse();
        assertThat(event.isEventDate(LocalDate.of(2019, 5, 6))).isFalse();
    }
}