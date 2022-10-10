package com.agr.atmservice.service;


import com.agr.atmservice.bankservice.ApiException;
import com.agr.atmservice.bankservice.model.CardDto.AuthMethodeEnum;
import com.agr.atmservice.bankservice.model.ChangeAuthTypeRequest;
import com.agr.atmservice.bankservice.model.SessionRequest;

public interface AuthService {
    boolean validateTransferToken(String token) throws ApiException;

    String createSession(SessionRequest sessionRequest) throws ApiException;

    AuthMethodeEnum getAuthType(String cardNumber) throws ApiException;

    void closeSession() throws ApiException;

    void changeAuthType(ChangeAuthTypeRequest changeAuthTypeRequest) throws ApiException;
}
