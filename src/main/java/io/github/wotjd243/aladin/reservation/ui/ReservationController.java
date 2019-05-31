package io.github.wotjd243.aladin.reservation.ui;

import io.github.wotjd243.aladin.reservation.application.ReservationService;
import io.github.wotjd243.aladin.reservation.application.dto.ReservationCreateDto;
import io.github.wotjd243.aladin.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ApiResponse add(@RequestHeader("user-id") String buyerId, @RequestBody @Valid ReservationCreateDto create) {

        reservationService.add(buyerId, create.getRegisteredBookId());

        return ApiResponse.createOK();
    }

    @DeleteMapping("{bookId}")
    public ApiResponse delete(@RequestHeader("user-id") String buyerId, @PathVariable("bookId") Long bookId) {

        reservationService.delete(buyerId, bookId);

        return ApiResponse.createOK();
    }
}
