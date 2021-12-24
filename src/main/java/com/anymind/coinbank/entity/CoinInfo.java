package com.anymind.coinbank.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "coin_info")
public class CoinInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "datetime")
    private String datetime;
}
