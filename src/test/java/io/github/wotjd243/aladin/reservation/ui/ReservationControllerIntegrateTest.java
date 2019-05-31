package io.github.wotjd243.aladin.reservation.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wotjd243.aladin.book.domain.RegisteredBook;
import io.github.wotjd243.aladin.book.domain.RegisteredBookRepository;
import io.github.wotjd243.aladin.common.domain.UnitAmount;
import io.github.wotjd243.aladin.person.domain.Buyer;
import io.github.wotjd243.aladin.person.domain.BuyerRepository;
import io.github.wotjd243.aladin.response.ApiResponse;
import io.github.wotjd243.aladin.response.ApiResponseCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerIntegrateTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private RegisteredBookRepository registeredBookRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        registeredBookRepository.deleteAll();
        buyerRepository.deleteAll();
    }

    @Test
    public void 성공_생성요청() throws Exception {

        //given
        String requestBody = "{\"bookId\":1}";
        Buyer buyer = createBuyer();
        createRegisteredBook();
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/reservation")
                .header("user-id", buyer.getId())
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.OK);
        assertThat(apiResponse.getMessage()).isEqualTo("요청이 성공하였습니다.");
    }

    private Buyer createBuyer() {

        return buyerRepository.save(
                Buyer.createBuilder()
                        .id("1")
                        .password("1234")
                        .name("이호진")
                        .phoneNumber("010-1111-1111")
                        .email("email@email.com")
                        .address("용인")
                        .build()
        );
    }

    private RegisteredBook createRegisteredBook() {

        return registeredBookRepository.save(
                RegisteredBook.builder()
                        .bookId(1L)
                        .unitAmount(new UnitAmount(10000L))
                        .build()
        );
    }

    private ApiResponse getApiResponse(MvcResult mvcResult) throws java.io.IOException {

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }

}
