package com.anymind.coinbank.service;

import com.anymind.coinbank.entity.BalanceInfo;
import com.anymind.coinbank.entity.CoinInfo;
import com.anymind.coinbank.mapper.ObjectMapper;
import com.anymind.coinbank.model.CoinInfoModel;
import com.anymind.coinbank.model.CoinListRequestModel;
import com.anymind.coinbank.model.CoinListResponseModel;
import com.anymind.coinbank.repository.BalanceInfoRepository;
import com.anymind.coinbank.repository.CoinInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class CoinInfoService {

    @Autowired
    private CoinInfoRepository coinInfoRepository;

    @Autowired
    private BalanceInfoRepository balanceInfoRepository;

    /**
     * Method to deposit coin
     * @param coinInfoModel
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public CoinInfoModel depositCoin(CoinInfoModel coinInfoModel) {
        try {
            CoinInfo coinInfo = ObjectMapper.OBJECT_MAPPER.modelToEntity(coinInfoModel);
            coinInfo.setTransactionType("deposit");

            BalanceInfo last = balanceInfoRepository.findTopByOrderByIdDesc();

            BalanceInfo balanceInfo = new BalanceInfo();
            balanceInfo.setBalance((last != null ? last.getBalance() : 0) + coinInfo.getAmount());
            Instant dateHoursOnly = coinInfo.getDatetime().toInstant().truncatedTo(ChronoUnit.HOURS);
            balanceInfo.setDatetime(dateHoursOnly);

            //if request got delayed or within same hour range
            if(last != null && last.getDatetime() != null && last.getDatetime().compareTo(dateHoursOnly) >= 0) {
                balanceInfoRepository.deleteById(last.getId());

                //delayed request treated as current deposit in balance only
                balanceInfo.setDatetime(last.getDatetime());
            }

            balanceInfoRepository.save(balanceInfo);
            return ObjectMapper.OBJECT_MAPPER.entityToModel(coinInfoRepository.save(coinInfo));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     * Method to get balance of coin wallet every hour
     * @param pageable
     * @param req
     * @return
     */
    @Transactional(readOnly = true)
    public Page<CoinListResponseModel> findCoins(Pageable pageable, CoinListRequestModel req) {
        try {
            Page<BalanceInfo> fromEntity = balanceInfoRepository.findByDatetimeBetweenOrderByDatetimeAsc(ZonedDateTime.parse(req.getStartDatetime()).toInstant(), ZonedDateTime.parse(req.getEndDatetime()).toInstant(), pageable);
            return fromEntity.map(entity -> ObjectMapper.OBJECT_MAPPER.entityToResponseModel(entity));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
