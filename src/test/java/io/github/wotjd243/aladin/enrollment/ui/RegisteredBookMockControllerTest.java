package io.github.wotjd243.aladin.enrollment.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wotjd243.aladin.book.application.RegisteredBookService;
import io.github.wotjd243.aladin.book.ui.RegisteredBookController;
import io.github.wotjd243.aladin.exception.NotFoundException;
import io.github.wotjd243.aladin.response.ApiResponse;
import io.github.wotjd243.aladin.response.ApiResponseCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {RegisteredBookController.class})
public class RegisteredBookMockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisteredBookService registeredBookService;


    @Test
    public void 새책_권수없이_등록요청() throws Exception {
        String requestBody = "{\"bookId\":10000}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/registered-books/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "cys")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("요청 파라미터가 잘못되었습니다.");
    }

    @Test
    public void 없는책_등록요청() throws Exception {

        //given
        String requestBody = "{\"bookId\":1,\"count\":10}";

        willThrow(new NotFoundException("책이 없습니다."))
                .given(registeredBookService)
                .save((HttpSession) any(), any());

        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/registered-books/new")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("user-id", "cys")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.NOT_FOUND);
        assertThat(apiResponse.getMessage()).isEqualTo("책이 없습니다.");
    }


    @Test
    public void 새책_성공요청() throws Exception {
        String requestBody = "{\"bookId\":1,\"count\":1}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/registered-books/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "cys")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.OK);
        assertThat(apiResponse.getMessage()).isEqualTo("요청이 성공하였습니다.");
    }

    @Test
    public void 중고책_가격없이_등록요청() throws Exception {
        String requestBody = "{\"bookId\":10000}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/registered-books/used")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "cys")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("요청 파라미터가 잘못되었습니다.");
    }

    @Test
    public void 중고책_가격_1000원_이하_등록요청() throws Exception {
        String requestBody = "{\"bookId\":10000,\"amount\":999}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/registered-books/used")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "cys")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("요청 파라미터가 잘못되었습니다.");
    }

    @Test
    public void 중고책_성공요청() throws Exception {
        String requestBody = "{\"bookId\":1,\"amount\":10000}";
        //given
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/registered-books/used")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestBody)
                .header("user-id", "cys")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.OK);
        assertThat(apiResponse.getMessage()).isEqualTo("요청이 성공하였습니다.");
    }


    private ApiResponse getApiResponse(MvcResult mvcResult) throws java.io.IOException {

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }
}
