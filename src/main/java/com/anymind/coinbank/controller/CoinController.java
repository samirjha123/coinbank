package com.anymind.coinbank.controller;

import com.anymind.coinbank.constants.Constants;
import com.anymind.coinbank.model.CoinInfoModel;
import com.anymind.coinbank.service.CoinInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping(Constants.COIN)
public class CoinController {

    @Autowired
    private CoinInfoService coinInfoService;

    /**
     * deosit coin
     * @param score
     * @return
     */
    @PostMapping(Constants.DEPOSIT)
    public ResponseEntity<CoinInfoModel> depositCoin(@Valid @RequestBody CoinInfoModel score) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(coinInfoService.depositCoin(score));
    }

    @GetMapping(value = Constants.LIST)
    public ResponseEntity<Page> scorePageable(Pageable pageable, @RequestParam(required = false)  String startTime, @RequestParam(required = false)  String endTime) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(coinInfoService.findCoins(pageable, startTime, endTime));
    }
}
