package com.anymind.coinbank.repository;

import com.anymind.coinbank.entity.BalanceInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface BalanceInfoRepository extends JpaRepository<BalanceInfo, Long>, JpaSpecificationExecutor<BalanceInfo> {

    Page<BalanceInfo> findByDatetimeBetweenOrderByDatetimeAsc(Instant startTime, Instant endTime, Pageable pageable);

    BalanceInfo findTopByOrderByIdDesc();

}
