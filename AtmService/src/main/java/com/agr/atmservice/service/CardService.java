package com.agr.atmservice.service;

import com.agr.atmservice.bankservice.ApiException;
import com.agr.atmservice.bankservice.model.AccountDto;
import com.agr.atmservice.bankservice.model.CardDto;

import java.math.BigDecimal;

public interface CardService {
    CardDto getCard() throws ApiException;

    void deposit(BigDecimal amount) throws ApiException;

    void withdrawal(BigDecimal amount) throws ApiException;

    AccountDto getAccountData() throws ApiException;
}
