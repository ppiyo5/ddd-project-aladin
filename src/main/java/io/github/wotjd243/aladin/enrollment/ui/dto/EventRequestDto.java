package io.github.wotjd243.aladin.enrollment.ui.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventRequestDto {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Double periodPercent;
}




