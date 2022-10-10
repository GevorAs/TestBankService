package com.agr.bankservice.api.request;

import lombok.Data;

@Data
public class SessionRequest {
    private String cardNumber;
    private String secret;
}
