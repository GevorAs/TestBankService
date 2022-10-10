package com.agr.bankservice.api.response;


import com.agr.bankservice.api.response.code.ResponseCode;

public class EmptyPositiveResponse extends Response {
    public EmptyPositiveResponse() {
        super(ResponseCode.OK);
    }
}
