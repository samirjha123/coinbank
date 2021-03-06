package com.anymind.coinbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.anymind.coinbank.constants.Constants;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Long id;

    @NotNull
    @DecimalMax("9999999.99999999") @DecimalMin("0.00000001")
    private Double amount;

    @NotNull
    @Pattern(regexp=Constants.DATE_REGEX, message="Invalid datetime")
    private String datetime;
}
