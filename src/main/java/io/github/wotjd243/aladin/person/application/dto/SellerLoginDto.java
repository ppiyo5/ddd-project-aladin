package io.github.wotjd243.aladin.person.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerLoginDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

}
