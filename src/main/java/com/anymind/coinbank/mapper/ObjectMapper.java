package com.anymind.coinbank.mapper;

import com.anymind.coinbank.entity.CoinInfo;
import com.anymind.coinbank.model.CoinInfoModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;

@Mapper
public interface ObjectMapper extends Serializable {

    ObjectMapper OBJECT_MAPPER = Mappers.getMapper(ObjectMapper.class);

    CoinInfo modelToEntity(CoinInfoModel coinInfoModel);

    CoinInfoModel entityToModel(CoinInfo coinInfo);
}