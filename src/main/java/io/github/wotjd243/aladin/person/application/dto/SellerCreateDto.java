package io.github.wotjd243.aladin.person.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerCreateDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])-\\d{3,4}-\\d{4}$")
    private String phoneNumber;

    @Email(regexp = "^(.+)@(.+)$")
    private String email;
}
