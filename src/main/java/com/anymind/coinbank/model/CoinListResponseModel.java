package com.anymind.coinbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinListResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Double balance;

    @NotNull
    private String datetime;
}