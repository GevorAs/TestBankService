package com.agr.atmservice.service.impl;

import com.agr.atmservice.bankservice.ApiException;
import com.agr.atmservice.bankservice.api.AccountControllerApi;
import com.agr.atmservice.bankservice.api.CardControllerApi;
import com.agr.atmservice.bankservice.model.AccountDto;
import com.agr.atmservice.bankservice.model.CardDto;
import com.agr.atmservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static com.agr.atmservice.security.interceptors.TokenServiceInterceptor.TOKEN;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    HttpServletRequest request;

    private final CardControllerApi cardController;
    private final AccountControllerApi accountControllerApi;

    public CardServiceImpl(CardControllerApi cardController,
                           AccountControllerApi accountControllerApi) {
        this.cardController = cardController;
        this.accountControllerApi = accountControllerApi;
    }

    @Override
    public CardDto getCard() throws ApiException {
        return cardController.getCard(getToken());
    }

    @Override
    public void deposit(BigDecimal amount) throws ApiException {
        cardController.deposit(getToken(), amount);
    }

    @Override
    public void withdrawal(BigDecimal amount) throws ApiException {
        cardController.withdrawal(getToken(), amount);
    }

    @Override
    public AccountDto getAccountData() throws ApiException {
        return accountControllerApi.getAccountData(getToken());
    }

    private String getToken() {
        return request.getHeader(TOKEN);
    }
}
