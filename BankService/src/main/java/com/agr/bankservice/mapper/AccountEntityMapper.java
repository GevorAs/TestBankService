package com.agr.bankservice.mapper;


import com.agr.bankservice.dto.AccountDto;
import com.agr.bankservice.model.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper extends EntityMapper<AccountDto, AccountEntity> {
}
