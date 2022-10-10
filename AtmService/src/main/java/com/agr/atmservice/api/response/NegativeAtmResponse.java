package com.agr.atmservice.api.response;

import com.agr.atmservice.api.response.code.ResponseCode;
import com.agr.atmservice.bankservice.ApiException;
import lombok.Getter;

@Getter
public class NegativeAtmResponse extends Response {
    private final String message;
    private final int code;

    public NegativeAtmResponse(RuntimeException accountException, int code) {
        super(ResponseCode.FAIL);
        this.message = accountException.getMessage();
        this.code = code;
    }

    public NegativeAtmResponse(ApiException accountException, int code) {
        super(ResponseCode.FAIL);
        this.message = accountException.getMessage();
        this.code = code;
    }
}
