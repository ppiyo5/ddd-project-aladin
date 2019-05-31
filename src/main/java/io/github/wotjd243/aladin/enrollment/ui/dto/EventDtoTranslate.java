package io.github.wotjd243.aladin.enrollment.ui.dto;

import io.github.wotjd243.aladin.enrollment.application.dto.EventDto;
import io.github.wotjd243.aladin.exception.WrongValueException;

import java.time.LocalDate;

public class EventDtoTranslate {

    public static EventDto translate(EventRequestDto request) {
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        Double periodPercent = request.getPeriodPercent();

        validateDate(startDate, endDate);
        validatePercent(periodPercent);
        return new EventDto(startDate, endDate, periodPercent);
    }

    private static void validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new WrongValueException("잘못된 기간설정 입니다.");
        }
    }

    private static void validatePercent(Double periodPercent) {
        if (periodPercent < 0) {
            throw new WrongValueException("잘못된 할인율입니다.");
        }
    }
}
