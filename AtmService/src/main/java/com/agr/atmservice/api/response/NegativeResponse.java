package com.agr.atmservice.api.response;

import com.agr.atmservice.api.response.code.ResponseCode;
import lombok.Getter;

@Getter
public class NegativeResponse extends Response {
    private final String message;
    private final int code;

    public NegativeResponse(String message, int code) {
        super(ResponseCode.FAIL);
        this.message = message;
        this.code = code;
    }
}
