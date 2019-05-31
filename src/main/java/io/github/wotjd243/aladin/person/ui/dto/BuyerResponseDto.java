package io.github.wotjd243.aladin.person.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerResponseDto {

    private String id;

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    @Builder(builderMethodName = "responseBuilder")
    private BuyerResponseDto(String id, String name, String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
