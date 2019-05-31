//package io.github.wotjd243.aladin.enrollment.domain;
//
//import io.github.wotjd243.aladin.enrollment.domain.Enrollment;
//import io.github.wotjd243.aladin.enrollment.domain.Event;
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class EnrollmentTest {
//
//    @Test
//    public void 이벤트없이_만원짜리_같은책_세권등록() {
//        //given
//        Enrollment enrollment = Enrollment.of(1L, 1L, 3L, 10000L, new ArrayList<>());
//        //expect
//        assertThat(enrollment.getRegisteredBooks().size()).isEqualTo(3);
//        assertThat(enrollment.getEvents().size()).isEqualTo(0);
//        //then
//    }
//
//    @Test
//    public void 이벤트있는_만원짜리_같은책_세권등록() {
//        //given
//        List<Event> events = Collections.singletonList(new Event(LocalDate.of(2019, 4, 22),
//                LocalDate.of(2019, 4, 24), 3.0));
//        Enrollment enrollment = Enrollment.of(1L, 1L, 3L, 10000L, events);
//        //expect
//        assertThat(enrollment.getRegisteredBooks().size()).isEqualTo(3);
//
//        Event event = enrollment.getEvents().get(0);
//
//        assertThat(event.getStartDate()).isEqualTo(LocalDate.of(2019, 4, 22));
//        assertThat(event.getEndDate()).isEqualTo(LocalDate.of(2019,4, 24));
//        assertThat(event.getPeriodPercent()).isEqualTo(3.0);
//        //then
//    }
//}
