package com.anymind.coinbank.controller;

import com.anymind.coinbank.constants.Constants;
import com.anymind.coinbank.model.CoinInfoModel;
import com.anymind.coinbank.model.CoinListRequestModel;
import com.anymind.coinbank.service.CoinInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.COIN)
public class CoinController {

    @Autowired
    private CoinInfoService coinInfoService;

    /**
     * deposit coin
     * @param coinInfoModel
     * @return
     */
    @PostMapping(Constants.DEPOSIT)
    @ResponseBody
    public ResponseEntity depositCoin(@Valid @RequestBody CoinInfoModel coinInfoModel) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(coinInfoService.depositCoin(coinInfoModel));
    }

    /**
     * list coins balance on each hour
     * @param pageable
     * @param req
     * @return
     */
    @PostMapping(value = Constants.LIST)
    @ResponseBody
    public ResponseEntity scorePageable(Pageable pageable, @Valid @RequestBody CoinListRequestModel req) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(coinInfoService.findCoins(pageable, req));
    }
}
