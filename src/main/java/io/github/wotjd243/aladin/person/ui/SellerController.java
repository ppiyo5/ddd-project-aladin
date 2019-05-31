package io.github.wotjd243.aladin.person.ui;

import io.github.wotjd243.aladin.person.application.SellerService;
import io.github.wotjd243.aladin.person.application.dto.SellerCreateDto;
import io.github.wotjd243.aladin.person.application.dto.SellerLoginDto;
import io.github.wotjd243.aladin.person.application.dto.SellerUpdateDto;
import io.github.wotjd243.aladin.person.infra.SellerTranslate;
import io.github.wotjd243.aladin.person.ui.dto.SellerResponseDto;
import io.github.wotjd243.aladin.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping(value = "/seller/{id}")
    public ApiResponse<SellerResponseDto> findSeller(@PathVariable String id) {

        return ApiResponse.createOK(SellerTranslate.translate(sellerService.findById(id)));
    }

    @PostMapping(value = "/seller")
    public ApiResponse<SellerResponseDto> createSeller(@RequestBody @Valid SellerCreateDto createDto) {

        return ApiResponse.createOK(SellerTranslate.translate(sellerService.createSeller(createDto)));
    }


    @PutMapping(value = "/seller")
    public ApiResponse<SellerResponseDto> updateSeller(@RequestHeader("user-id") String id, @RequestBody @Valid SellerUpdateDto updateDto) {

        return ApiResponse.createOK(SellerTranslate.translate(sellerService.updateSeller(id, updateDto)));
    }

    @PostMapping(value = "/seller/login")
    public ApiResponse<SellerResponseDto> loginSeller(@Valid @RequestBody SellerLoginDto loginDto) {
        return ApiResponse.createOK(SellerTranslate.translate(sellerService.loginSeller(loginDto)));
    }
}
