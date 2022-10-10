package com.agr.bankservice.api.response;

import com.agr.bankservice.api.response.code.ResponseCode;
import com.agr.bankservice.exception.BankException;
import lombok.Getter;

@Getter
public class NegativeBankResponse extends Response {
    private final String message;
    private final String description;
    private final int code;

    public NegativeBankResponse(BankException accountException, int code) {
        super(ResponseCode.FAIL);
        this.message = accountException.getMessage();
        this.description = accountException.getDescription();
        this.code = code;
    }
}
