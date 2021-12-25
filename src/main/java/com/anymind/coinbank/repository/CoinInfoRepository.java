package com.anymind.coinbank.repository;

import com.anymind.coinbank.entity.CoinInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface CoinInfoRepository extends JpaRepository<CoinInfo, Long>, JpaSpecificationExecutor<CoinInfo> {

    Page<CoinInfo> findByDatetimeBetween(ZonedDateTime startTime, ZonedDateTime endTime, Pageable pageable);


}
