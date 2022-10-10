package com.agr.atmservice.api.response;


import com.agr.atmservice.api.response.code.ResponseCode;

public class EmptyPositiveResponse extends Response {
    public EmptyPositiveResponse() {
        super(ResponseCode.OK);
    }
}
