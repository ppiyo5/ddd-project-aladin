package io.github.wotjd243.aladin.person.infra;

import io.github.wotjd243.aladin.person.application.dto.SellerCreateDto;
import io.github.wotjd243.aladin.person.domain.Seller;
import io.github.wotjd243.aladin.person.ui.dto.SellerResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerTranslate {

    public static Seller translate(SellerCreateDto sellerRequestDto) {

        return Seller.createBuilder()
                .id(sellerRequestDto.getId())
                .password(sellerRequestDto.getPassword())
                .name(sellerRequestDto.getName())
                .phoneNumber(sellerRequestDto.getPhoneNumber())
                .email(sellerRequestDto.getEmail())
                .build();
    }

    public static SellerResponseDto translate(Seller seller) {

        return SellerResponseDto.responseBuilder()
                .id(seller.getId())
                .name(seller.getUser().getName().getName())
                .phoneNumber(seller.getUser().getPhoneNumber().getPhoneNumber())
                .email(seller.getUser().getEmail().getEmail())
                .build();
    }
}
