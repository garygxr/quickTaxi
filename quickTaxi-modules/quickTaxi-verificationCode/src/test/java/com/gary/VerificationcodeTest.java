package com.gary;


import com.gary.VerificationCodeApplication;


import com.gary.constant.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import javax.servlet.http.HttpServletResponse;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = VerificationCodeApplication.class)
@AutoConfigureMockMvc
public class VerificationcodeTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void verificationCode() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
                .get("/verify-code/generate/1/13702079277")
                .contentType(MediaType.APPLICATION_JSON)
        );
         perform.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.SUCCESS));
    }

}
