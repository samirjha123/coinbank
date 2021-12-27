package com.anymind.coinbank.repository;

import com.anymind.coinbank.entity.CoinInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface CoinInfoRepository extends JpaRepository<CoinInfo, Long>, JpaSpecificationExecutor<CoinInfo> {

    @Query(value = "select id, transaction_type, amount, date_format(date_time,'%Y-%c-%d %H:00:00') as date_time, zone_id, \n" +
            "        sum(balance) as balance \n" +
            "        from coin_info \n" +
            "        where balance >= 1000 \n" +
            "        and date_time BETWEEN :startTime AND :endTime \n" +
            "        group by date_time, id", nativeQuery = true)
    Page<CoinInfo> findAllBalanceBetweenDatetime(ZonedDateTime startTime, ZonedDateTime endTime, Pageable pageable);

    CoinInfo findTopByOrderByIdDesc();


}
