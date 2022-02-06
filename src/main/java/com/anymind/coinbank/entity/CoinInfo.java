package com.anymind.coinbank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "coininfo")
public class CoinInfo {
    @Id
    private String id;

    private String transactionType;

    private Double amount;

    private String datetime;

    private String zoneId;
}
