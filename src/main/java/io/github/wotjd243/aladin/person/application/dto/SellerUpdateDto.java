package io.github.wotjd243.aladin.person.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerUpdateDto {

    private String password;

    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])-\\d{3,4}-\\d{4}$")
    private String phoneNumber;

}
