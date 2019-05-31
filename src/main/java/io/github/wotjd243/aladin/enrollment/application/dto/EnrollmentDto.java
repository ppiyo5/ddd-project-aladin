package io.github.wotjd243.aladin.enrollment.application.dto;

import io.github.wotjd243.aladin.enrollment.domain.Enrollment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollmentDto {

    private String sellerId;

    private EventDto eventDto;

    @Builder
    public EnrollmentDto(String sellerId, EventDto eventDto) {
        this.eventDto = eventDto;
        this.sellerId = sellerId;
    }

    public static EnrollmentDto of(String sellerId, EventDto eventDto) {
        return EnrollmentDto.builder()
                .sellerId(sellerId)
                .eventDto(eventDto)
                .build();
    }

    public Enrollment toEntity() {
        return new Enrollment(sellerId, Collections.singletonList(eventDto.toEntity()));

    }

}
