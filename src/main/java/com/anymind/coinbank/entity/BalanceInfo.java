package com.anymind.coinbank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "balanceinfo")
public class BalanceInfo {
    @Id
    private String id;

    private Double balance;

    private Instant datetime;
}
