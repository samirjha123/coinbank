package com.anymind.coinbank.repository;

import com.anymind.coinbank.entity.BalanceInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface BalanceInfoRepository extends MongoRepository<BalanceInfo, String> {

    Page<BalanceInfo> findByDatetimeBetweenOrderByDatetimeAsc(Instant startTime, Instant endTime, Pageable pageable);

    BalanceInfo findTopByOrderByIdDesc();

}
