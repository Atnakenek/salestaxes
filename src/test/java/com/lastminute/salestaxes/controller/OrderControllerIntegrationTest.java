package com.lastminute.salestaxes.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class OrderControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getReceiptOneTwoThree() throws Exception {
        mockMvc.perform(post("/orders/receipt")
                .content("[1,2,3]")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.products[0].price").value("12.49"))
                .andExpect(jsonPath("$.products[1].price").value("16.49"))
                .andExpect(jsonPath("$.products[2].price").value("0.85"))
                .andExpect(jsonPath("$.salesTaxes").value("1.5"))
                .andExpect(jsonPath("$.total").value("29.83"))
                .andReturn();
    }

    @Test
    public void getReceiptFourFive() throws Exception {
        mockMvc.perform(post("/orders/receipt")
                .content("[4,5]")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.products[0].price").value("10.50"))
                .andExpect(jsonPath("$.products[1].price").value("54.65"))
                .andExpect(jsonPath("$.salesTaxes").value("7.65"))
                .andExpect(jsonPath("$.total").value("65.15"))
                .andReturn();
    }

    @Test
    public void getReceiptSixSevenEightNine() throws Exception {
        mockMvc.perform(post("/orders/receipt")
                .content("[6,7,8,9]")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.products[0].price").value("32.19"))
                .andExpect(jsonPath("$.products[1].price").value("20.89"))
                .andExpect(jsonPath("$.products[2].price").value("9.75"))
                .andExpect(jsonPath("$.products[3].price").value("11.85"))
                .andExpect(jsonPath("$.salesTaxes").value("6.7"))
                .andExpect(jsonPath("$.total").value("74.68"))
                .andReturn();
    }

    @Test
    public void getReceiptOneNine() throws Exception {
        mockMvc.perform(post("/orders/receipt")
                .content("[1,9]")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.products[0].price").value("12.49"))
                .andExpect(jsonPath("$.products[1].price").value("11.85"))
                .andExpect(jsonPath("$.salesTaxes").value("0.6"))
                .andExpect(jsonPath("$.total").value("24.34"))
                .andReturn();
    }

    @Test
    public void getReceiptEmpty() throws Exception {
        mockMvc.perform(post("/orders/receipt")
                .content("[]")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.products").isEmpty())
                .andExpect(jsonPath("$.salesTaxes").value("0"))
                .andExpect(jsonPath("$.total").value("0"))
                .andReturn();
    }
}