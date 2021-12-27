package com.anymind.coinbank.service;

import com.anymind.coinbank.entity.CoinInfo;
import com.anymind.coinbank.mapper.ObjectMapper;
import com.anymind.coinbank.model.CoinInfoModel;
import com.anymind.coinbank.model.CoinListResponseModel;
import com.anymind.coinbank.repository.CoinInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@Slf4j
public class CoinInfoService {

    @Autowired
    private CoinInfoRepository coinInfoRepository;

    /**
     * Method to deposit coin
     * @param coinInfoModel
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public CoinInfoModel depositCoin(CoinInfoModel coinInfoModel) {
        try {
            CoinInfo coinInfo = ObjectMapper.OBJECT_MAPPER.modelToEntity(coinInfoModel);
            CoinInfo last = coinInfoRepository.findTopByOrderByIdDesc();
            coinInfo.setTransactionType("deposit");
            coinInfo.setBalance((last != null ? last.getBalance() : 0) + coinInfo.getAmount());
            return ObjectMapper.OBJECT_MAPPER.entityToModel(coinInfoRepository.save(coinInfo));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     * Method to get balance of coin wallet every hour
     * @param pageable
     * @param startTime
     * @param endTime
     * @return
     */
    @Transactional(readOnly = true)
    public Page<CoinListResponseModel> findCoins(Pageable pageable, String startTime, String endTime) {
        try {
            Page<CoinInfo> fromEntity = coinInfoRepository.findAllBalanceBetweenDatetime(ZonedDateTime.parse(startTime), ZonedDateTime.parse(endTime), pageable);
            return fromEntity.map(entity -> {
                CoinListResponseModel dto = ObjectMapper.OBJECT_MAPPER.entityToResponseModel(entity);
                return dto;
            });
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
