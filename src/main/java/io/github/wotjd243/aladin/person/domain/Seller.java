package io.github.wotjd243.aladin.person.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {

    @Id
    private String id;

    @Embedded
    private User user;

    @Builder(builderMethodName = "createBuilder")
    private Seller(final String id, final String password, final String name, final String phoneNumber, final String email) {
        this.id = id;
        this.user = new User(password, name, phoneNumber, email);
    }


    public void update(String password, String name, String phoneNumber) {
        user.update(password, name, phoneNumber);
    }
}
