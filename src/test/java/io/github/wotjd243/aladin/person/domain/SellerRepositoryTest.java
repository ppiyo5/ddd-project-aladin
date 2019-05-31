package io.github.wotjd243.aladin.person.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerRepositoryTest {

    @Autowired
    private SellerRepository repository;

    @Test
    public void Seller_조회() {

        // given
        Seller seller = createSeller();

        // when
        Optional<Seller> resultSeller = repository.findById("test");

        // then
        assertThat(resultSeller.get().getId()).isEqualTo(seller.getId());

    }

    public Seller createSeller() {
        return repository.save(Seller.createBuilder()
                .id("test")
                .password("password")
                .name("sehee")
                .phoneNumber("010-1111-2222")
                .email("sehee@naver.com")
                .build());
    }


}
