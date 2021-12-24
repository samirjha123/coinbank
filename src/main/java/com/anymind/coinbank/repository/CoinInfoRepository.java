package com.anymind.coinbank.repository;

import com.anymind.coinbank.entity.CoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinInfoRepository extends JpaRepository<CoinInfo, Long>, JpaSpecificationExecutor<CoinInfo> {
}
