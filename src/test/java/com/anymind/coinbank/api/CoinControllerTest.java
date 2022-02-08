package com.anymind.coinbank.api;

import com.anymind.coinbank.controller.CoinController;
import com.anymind.coinbank.model.CoinInfoModel;
import com.anymind.coinbank.model.CoinListRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CoinControllerTest {

    @Autowired
    private CoinController controller;

    @Autowired
    private MockMvc mockMvc;


    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void testDepositCoin() throws Exception {

        CoinInfoModel coinInfo = new CoinInfoModel();
        coinInfo.setAmount(25.0);
        coinInfo.setDatetime("2021-08-16T20:43:39+05:30");

        String json = mapper.writeValueAsString(coinInfo);
        mockMvc.perform(post("/coin/deposit").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void testDepositCoinInvalidAmount() throws Exception {

        CoinInfoModel coinInfo = new CoinInfoModel();
        coinInfo.setAmount(10000000.0);
        coinInfo.setDatetime("2021-08-16T20:43:39+05:30");

        String json = mapper.writeValueAsString(coinInfo);
        mockMvc.perform(post("/coin/deposit").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void testDepositCoinInvalidDate() throws Exception {

        CoinInfoModel coinInfo = new CoinInfoModel();
        coinInfo.setAmount(100.0);
        coinInfo.setDatetime("2021-13-16T20:43:39+05:30");

        String json = mapper.writeValueAsString(coinInfo);
        mockMvc.perform(post("/coin/deposit").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void testListCoin() throws Exception {

        CoinListRequestModel coinInfo = new CoinListRequestModel();
        coinInfo.setStartDatetime("2021-08-16T07:43:39+05:30");
        coinInfo.setEndDatetime("2021-08-16T20:43:39+05:30");
        String json = mapper.writeValueAsString(coinInfo);

        mockMvc.perform(post("/coin/list").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testListCoinInvalidStartDate() throws Exception {

        CoinListRequestModel coinInfo = new CoinListRequestModel();
        coinInfo.setStartDatetime("20216-08-16T07:43:39+05:30");
        coinInfo.setEndDatetime("2021-08-16T20:43:39+05:30");
        String json = mapper.writeValueAsString(coinInfo);

        mockMvc.perform(post("/coin/list").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void testListCoinInvalidEndDate() throws Exception {

        CoinListRequestModel coinInfo = new CoinListRequestModel();
        coinInfo.setStartDatetime("2021-08-16T07:43:39+05:30");
        coinInfo.setEndDatetime("2021-08-16T25:43:39+05:30");
        String json = mapper.writeValueAsString(coinInfo);

        mockMvc.perform(post("/coin/list").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}
