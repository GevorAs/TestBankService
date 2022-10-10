package com.agr.bankservice.service;

import com.agr.bankservice.dto.CardDto;
import com.agr.bankservice.model.CardEntity;
import com.agr.bankservice.model.enums.CardAuthMethodeEnum;

import javax.transaction.Transactional;
import java.math.BigDecimal;

public interface CardService {

    CardAuthMethodeEnum getAuthType(String cardNumber);

    CardDto getCard(String token);


    CardEntity getCardEntity(String token);

    @Transactional()
    void deposit(BigDecimal amount, String token);

    @Transactional
    void withdrawal(BigDecimal amount, String token);
}
