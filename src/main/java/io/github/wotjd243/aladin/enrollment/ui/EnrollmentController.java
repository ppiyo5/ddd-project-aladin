package io.github.wotjd243.aladin.enrollment.ui;

import io.github.wotjd243.aladin.common.HttpSessionUtil;
import io.github.wotjd243.aladin.enrollment.application.EnrollmentService;
import io.github.wotjd243.aladin.enrollment.application.dto.EnrollmentDto;
import io.github.wotjd243.aladin.enrollment.ui.dto.EventDtoTranslate;
import io.github.wotjd243.aladin.enrollment.ui.dto.EventRequestDto;
import io.github.wotjd243.aladin.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ApiResponse save(HttpSession session, @RequestHeader("user-id") String sellerId, @RequestBody @Valid EventRequestDto requestDto) {
        enrollmentService.save(EnrollmentDto.of(sellerId, EventDtoTranslate.translate(requestDto)),
                HttpSessionUtil.getSessionRegisteredBook(session));

        return ApiResponse.createOK();
    }
}
