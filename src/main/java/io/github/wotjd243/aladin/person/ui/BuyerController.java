package io.github.wotjd243.aladin.person.ui;

import io.github.wotjd243.aladin.person.application.BuyerService;
import io.github.wotjd243.aladin.person.application.dto.BuyerCreateDto;
import io.github.wotjd243.aladin.person.application.dto.BuyerLoginDto;
import io.github.wotjd243.aladin.person.application.dto.BuyerUpdateDto;
import io.github.wotjd243.aladin.person.domain.Buyer;
import io.github.wotjd243.aladin.person.infra.BuyerTranslate;
import io.github.wotjd243.aladin.person.ui.dto.BuyerResponseDto;
import io.github.wotjd243.aladin.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    @GetMapping(value = "/buyer/{id}")
    public ApiResponse<BuyerResponseDto> findBuyer(@PathVariable String id) {

        return ApiResponse.createOK(BuyerTranslate.translate(buyerService.findById(id)));
    }

    @PostMapping(value = "/buyer")
    public ApiResponse<BuyerResponseDto> createBuyer(@Valid @RequestBody BuyerCreateDto createDto) {

        return ApiResponse.createOK(BuyerTranslate.translate(buyerService.createBuyer(createDto)));
    }

    @PutMapping(value = "/buyer")
    public ApiResponse<BuyerResponseDto> updateBuyer(@RequestHeader("user-id") String id, @Valid @RequestBody BuyerUpdateDto updateDto) {

        return ApiResponse.createOK(BuyerTranslate.translate(buyerService.updateBuyer(id, updateDto)));
    }

    @PostMapping(value = "/buyer/login")
    public ApiResponse<BuyerResponseDto> loginBuyer(@Valid @RequestBody BuyerLoginDto loginDto) {
        return ApiResponse.createOK(BuyerTranslate.translate(buyerService.loginBuyer(loginDto)));
    }
}
