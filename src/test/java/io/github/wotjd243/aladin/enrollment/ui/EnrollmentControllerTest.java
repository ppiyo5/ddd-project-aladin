package io.github.wotjd243.aladin.enrollment.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wotjd243.aladin.enrollment.domain.EnrollmentRepository;
import io.github.wotjd243.aladin.response.ApiResponse;
import io.github.wotjd243.aladin.response.ApiResponseCode;
import org.junit.After;
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
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @After
    public void tearDown() throws Exception {
        enrollmentRepository.deleteAll();
    }

    @Test
    public void 등록된_책없이_저장시도() throws Exception {

        //given
        String requestBody = "{\"startDate\":\"2019-05-08\",\"endDate\":\"2019-05-09\",\"periodPercent\":10}";
        ;

        MvcResult mvcResult = this.mockMvc.perform(post("/enrollment")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("user-id", "cys")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        //then
        ApiResponse apiResponse = getApiResponse(mvcResult);

        assertThat(apiResponse.getCode()).isEqualTo(ApiResponseCode.BAD_PARAMETER);
        assertThat(apiResponse.getMessage()).isEqualTo("등록된 책이 없습니다.");

    }

    ApiResponse getApiResponse(MvcResult mvcResult) throws java.io.IOException {

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(mockHttpServletResponse.getContentAsString(), ApiResponse.class);
    }

}