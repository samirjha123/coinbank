package com.anymind.coinbank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private Double amount;

    @NotNull
    private String datetime;
}
