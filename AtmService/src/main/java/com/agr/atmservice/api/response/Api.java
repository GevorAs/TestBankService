package com.agr.atmservice.api.response;

import com.agr.atmservice.bankservice.ApiException;

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

    public static Response negativeResponse(RuntimeException e, int code) {
        return new NegativeAtmResponse(e, code);
    }

    public static Response negativeResponse(ApiException e, int code) {
        return new NegativeAtmResponse(e, code);
    }
}
