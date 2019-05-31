package io.github.wotjd243.aladin.common.domain;

import io.github.wotjd243.aladin.exception.NotFoundException;
import io.github.wotjd243.aladin.exception.WrongValueException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Email {

    private final static Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");

    private String email;

    public Email(String email) {

        this.email = validation(email);
    }

    private String validation(String email) {

        if (StringUtils.isEmpty(email)) {
            throw new NotFoundException("이메일이 없습니다.");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new WrongValueException(String.format("이메일이 잘못되었습니다. [xxx@xxxx.com] [%s]", email));
        }

        return email;
    }
}
