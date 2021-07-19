package com.example.springbootprojectdemo.controller;

import com.example.springbootprojectdemo.dto.StaticRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ApiController.class) //WEB에 필요한 빈만 등록
@AutoConfigureWebMvc
//@SpringBootTest //모든 빈이 등록된다
//@Import(***.class) 특정 클래스 등록
public class ApiControllerTest {

    @MockBean
    //private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
       // Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    //get Test
    @Test
    public void sumTest(){
        try {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                            .queryParam("x", "10").queryParam("y", "10")
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
            ).andExpect(
                    MockMvcResultMatchers.content().string("60000")
            ).andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
        }
    }

    @Test
    public void minusTest() {
        StaticRequest request = new StaticRequest();
        //set request content
        ///

        try {
            String json = new ObjectMapper().writeValueAsString(request);

            mockMvc.perform(
                    MockMvcRequestBuilders.get("http://localhost:8080/api/minus")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
            ).andExpect(
                    MockMvcResultMatchers.status().isOk()
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.result").value("0")
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
            ).andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
        }
    }




}
