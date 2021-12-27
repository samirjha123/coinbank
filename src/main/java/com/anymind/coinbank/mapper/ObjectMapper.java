package com.anymind.coinbank.mapper;

import com.anymind.coinbank.entity.CoinInfo;
import com.anymind.coinbank.model.CoinInfoModel;
import com.anymind.coinbank.model.CoinListResponseModel;
import org.mapstruct.*;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Mapper
public interface ObjectMapper extends Serializable {

    ObjectMapper OBJECT_MAPPER = Mappers.getMapper(ObjectMapper.class);

    @Mapping(target = "zoneId", ignore = true)
    @Mapping(target = "datetime", ignore = true)
    CoinInfo modelToEntity(CoinInfoModel coinInfoModel);

    CoinInfoModel entityToModel(CoinInfo coinInfo);

    @AfterMapping
    default void setDatetime(CoinInfoModel coinInfoModel, @MappingTarget CoinInfo coinInfo){
        coinInfo.setDatetime(ZonedDateTime.parse(coinInfoModel.getDatetime()));
    }

    @AfterMapping
    default void setZoneId(CoinInfoModel coinInfoModel, @MappingTarget CoinInfo coinInfo){
       coinInfo.setZoneId(ZonedDateTime.parse(coinInfoModel.getDatetime()).getZone().toString());
    }

    CoinListResponseModel entityToResponseModel(CoinInfo coinInfo);

    @AfterMapping
    default void setDatetime(CoinInfo coinInfoModel, @MappingTarget CoinListResponseModel coinInfo){
        coinInfo.setDatetime(ZonedDateTime.parse(String.valueOf(coinInfoModel.getDatetime())).toString());
    }
}