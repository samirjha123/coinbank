package com.anymind.coinbank.repository;

import com.anymind.coinbank.entity.CoinInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinInfoRepository extends MongoRepository<CoinInfo, String> {
}
