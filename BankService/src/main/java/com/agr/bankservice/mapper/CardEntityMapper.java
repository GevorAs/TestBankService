package com.agr.bankservice.mapper;


import com.agr.bankservice.dto.CardDto;
import com.agr.bankservice.model.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardEntityMapper extends EntityMapper<CardDto, CardEntity> {
}
