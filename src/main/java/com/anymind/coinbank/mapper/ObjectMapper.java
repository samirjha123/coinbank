package com.anymind.coinbank.mapper;

import com.anymind.coinbank.entity.BalanceInfo;
import com.anymind.coinbank.entity.CoinInfo;
import com.anymind.coinbank.model.CoinInfoModel;
import com.anymind.coinbank.model.CoinListResponseModel;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

@Mapper
public interface ObjectMapper extends Serializable {

    ObjectMapper OBJECT_MAPPER = Mappers.getMapper(ObjectMapper.class);

    @Mapping(target = "zoneId", ignore = true)
    @Mapping(target = "datetime", ignore = true)
    CoinInfo modelToEntity(CoinInfoModel coinInfoModel);

    CoinInfoModel entityToModel(CoinInfo coinInfo);

    @AfterMapping
    default void setDatetime(CoinInfoModel coinInfoModel, @MappingTarget CoinInfo coinInfo) {
        try {
            coinInfo.setDatetime(ZonedDateTime.parse(coinInfoModel.getDatetime()));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to format datetime: "+ coinInfoModel.getDatetime());
        }
    }

    @AfterMapping
    default void setZoneId(CoinInfoModel coinInfoModel, @MappingTarget CoinInfo coinInfo) {
        try {
            coinInfo.setZoneId(String.valueOf(ZonedDateTime.parse(coinInfoModel.getDatetime()).getZone()));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Unable to format datetime: " + coinInfoModel.getDatetime());
        }
    }

    CoinListResponseModel entityToResponseModel(BalanceInfo balanceInfo);
}