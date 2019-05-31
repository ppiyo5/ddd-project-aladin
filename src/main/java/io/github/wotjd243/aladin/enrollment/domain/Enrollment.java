package io.github.wotjd243.aladin.enrollment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sellerId;

    @OneToMany(mappedBy = "enrollment", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Event> events = new ArrayList<>();

    public Enrollment(String sellerId, List<Event> events) {
        this.sellerId = sellerId;
        addEvents(events);
    }

    private void addEvents(List<Event> events) {
        this.events.addAll(events);
        events.forEach(event -> event.setEnrollment(this));
    }

    public Optional<Event> getEvent(LocalDate searchDate) {
        return events.stream()
                .filter(event -> event.isEventDate(searchDate))
                .findFirst();
    }
}
