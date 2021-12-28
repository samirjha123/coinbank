package com.anymind.coinbank.model;

import com.anymind.coinbank.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinListRequestModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp=Constants.DATE_REGEX, message="Invalid startDatetime")
    private String startDatetime;

    @NotNull
    @Pattern(regexp= Constants.DATE_REGEX, message="Invalid endDatetime")
    private String endDatetime;
}
