package com.agr.bankservice.service.impl;

import com.agr.bankservice.dto.AccountDto;
import com.agr.bankservice.exception.AccountException;
import com.agr.bankservice.mapper.AccountEntityMapper;
import com.agr.bankservice.model.AccountEntity;
import com.agr.bankservice.repository.AccountRepo;
import com.agr.bankservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.agr.bankservice.service.AuthService.TOKEN;

//todo Account CRUD
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    HttpServletRequest request;

    private final AccountRepo accountRepo;
    private final AccountEntityMapper accountEntityMapper;

    public AccountServiceImpl(AccountRepo accountRepo, AccountEntityMapper accountEntityMapper) {
        this.accountRepo = accountRepo;
        this.accountEntityMapper = accountEntityMapper;
    }

    @Override
    public AccountDto getAccount() {
        AccountEntity accountEntity = accountRepo.getAccountBySessionToken(getToken()).orElseThrow(AccountException::new);
        return accountEntityMapper.toDto(accountEntity);
    }

    private String getToken() {
        return request.getHeader(TOKEN);
    }
}
