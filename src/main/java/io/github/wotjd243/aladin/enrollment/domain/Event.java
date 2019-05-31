package io.github.wotjd243.aladin.enrollment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double periodPercent;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    public Event(LocalDate startDate, LocalDate endDate, Double periodPercent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodPercent = periodPercent;
    }

    public boolean isEventDate(LocalDate searchDate) {
        return endDate.plusDays(1).isAfter(searchDate) && startDate.minusDays(1).isBefore(searchDate);
    }
}
