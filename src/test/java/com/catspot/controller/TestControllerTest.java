package com.catspot.controller;

import com.catspot.exceptionhandler.CommonErrorCode;
import com.catspot.exceptionhandler.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestController.class)
@TestPropertySource(properties = {"spring.application.name=TestApplication"})
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloCatspot() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello catspot!"));
    }

    @Test
    public void testAppName() throws Exception {
        mockMvc.perform(get("/test2"))
                .andExpect(status().isOk())
                .andExpect(content().string("TestApplication"));
    }

    @Test
    public void testRuntimeException() throws Exception {
        mockMvc.perform(get("/exception1"))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertTrue("테스트 예외 발생".equals(result.getResolvedException().getMessage())));
    }

    @Test
    public void testCustomException() throws Exception {
        mockMvc.perform(get("/exception2"))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomException))
                .andExpect(result -> assertTrue(((CustomException) result.getResolvedException()).getErrorCode() == CommonErrorCode.INTERNAL_SERVER_ERROR));
    }

}
