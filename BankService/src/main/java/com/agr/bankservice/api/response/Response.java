package com.agr.bankservice.api.response;

import com.agr.bankservice.api.response.code.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public abstract class Response implements Serializable {
    private final ResponseCode result;
}
