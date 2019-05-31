package io.github.wotjd243.aladin.person.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerResponseDto {

    private String id;

    private String name;

    private String phoneNumber;

    private String email;

    @Builder(builderMethodName = "responseBuilder")
    private SellerResponseDto(String id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
