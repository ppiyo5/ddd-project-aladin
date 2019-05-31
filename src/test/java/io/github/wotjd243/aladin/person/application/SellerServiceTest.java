package io.github.wotjd243.aladin.person.application;

import io.github.wotjd243.aladin.person.domain.Seller;
import io.github.wotjd243.aladin.person.domain.SellerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
    private SellerService sellerService;

    @Test
    public void Id로_판매자_조회() {
        // given
        given(sellerRepository.findById(any()))
                .willReturn(
                        Optional.of(
                                Seller.createBuilder()
                                        .id("sehee")
                                        .name("sehee")
                                        .password("password")
                                        .phoneNumber("010-1234-5678")
                                        .email("test@naver.com")
                                        .build()

                        )
                )
        ;

        Seller seller = Seller.createBuilder()
                .id("sehee")
                .name("sehee")
                .password("password")
                .phoneNumber("010-1234-5678")
                .email("test@naver.com")
                .build();

        // when
        Seller seller1 = sellerService.findById("sehee");

        // then
        assertThat(seller1.getUser().getName().getName()).isEqualTo(seller.getUser().getName().getName());
    }
}
