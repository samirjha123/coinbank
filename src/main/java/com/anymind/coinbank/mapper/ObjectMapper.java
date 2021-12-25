package com.anymind.coinbank.mapper;

import com.anymind.coinbank.entity.CoinInfo;
import com.anymind.coinbank.model.CoinInfoModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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
}