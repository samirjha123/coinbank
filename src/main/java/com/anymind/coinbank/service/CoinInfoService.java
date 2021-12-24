package com.anymind.coinbank.service;

import com.anymind.coinbank.entity.CoinInfo;
import com.anymind.coinbank.mapper.ObjectMapper;
import com.anymind.coinbank.model.CoinInfoModel;
import com.anymind.coinbank.repository.CoinInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CoinInfoService {

    @Autowired
    private CoinInfoRepository coinInfoRepository;

    /**
     *
     * @param coinInfoModel
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public CoinInfoModel depositCoin(CoinInfoModel coinInfoModel) {
        try {
            CoinInfo coinInfo = ObjectMapper.OBJECT_MAPPER.modelToEntity(coinInfoModel);
            return ObjectMapper.OBJECT_MAPPER.entityToModel(coinInfoRepository.save(coinInfo));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     *
     * @param pageable
     * @param startTime
     * @param endTime
     * @return
     */
    @Transactional(readOnly = true)
    public Page<CoinInfo> findCoins(Pageable pageable, String startTime, String endTime) {
        try {
            return coinInfoRepository.findAll(pageable);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
