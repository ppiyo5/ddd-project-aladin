package io.github.wotjd243.aladin.person.infra;

import io.github.wotjd243.aladin.person.application.dto.BuyerCreateDto;
import io.github.wotjd243.aladin.person.domain.Buyer;
import io.github.wotjd243.aladin.person.ui.dto.BuyerResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerTranslate {

    public static Buyer translate(BuyerCreateDto buyerRequestDto) {

        return Buyer.createBuilder()
                .id(buyerRequestDto.getId())
                .password(buyerRequestDto.getPassword())
                .name(buyerRequestDto.getName())
                .phoneNumber(buyerRequestDto.getPhoneNumber())
                .email(buyerRequestDto.getEmail())
                .address(buyerRequestDto.getAddress())
                .build();
    }

    public static BuyerResponseDto translate(Buyer buyer) {

        return BuyerResponseDto.responseBuilder()
                .id(buyer.getId())
                .name(buyer.getUser().getName().getName())
                .phoneNumber(buyer.getUser().getPhoneNumber().getPhoneNumber())
                .email(buyer.getUser().getEmail().getEmail())
                .address(buyer.getAddress().getAddress())
                .build();
    }
}
