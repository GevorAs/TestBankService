package com.agr.bankservice.dto;

import com.agr.bankservice.model.enums.CardAuthMethodeEnum;
import com.agr.bankservice.model.enums.CardStateEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDto {
    private String cardNumber;

    private CardAuthMethodeEnum authMethode;

    private BigDecimal balance;

    private CardStateEnum state;
}
