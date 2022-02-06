package com.anymind.coinbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinListResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private String datetime;
}