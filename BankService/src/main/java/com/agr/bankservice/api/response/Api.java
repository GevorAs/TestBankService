package com.agr.bankservice.api.response;

import com.agr.bankservice.exception.BankException;

public class Api {
    public static Response emptyPositiveResponse() {
        return new EmptyPositiveResponse();
    }

    public static Response emptyNegativeResponse() {
        return new EmptyPositiveResponse();
    }

    public static Response negativeResponse(String message, int code) {
        return new NegativeResponse(message, code);
    }

    public static Response negativeResponse(BankException e, int code) {
        return new NegativeBankResponse(e, code);
    }
}
