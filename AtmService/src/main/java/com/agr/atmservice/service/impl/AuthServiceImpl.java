package com.agr.atmservice.service.impl;

import com.agr.atmservice.bankservice.ApiException;
import com.agr.atmservice.bankservice.api.AuthControllerApi;
import com.agr.atmservice.bankservice.api.CardControllerApi;
import com.agr.atmservice.bankservice.model.CardDto.AuthMethodeEnum;
import com.agr.atmservice.bankservice.model.ChangeAuthTypeRequest;
import com.agr.atmservice.bankservice.model.SessionRequest;
import com.agr.atmservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.agr.atmservice.security.interceptors.TokenServiceInterceptor.TOKEN;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    HttpServletRequest request;

    private final AuthControllerApi authController;
    private final CardControllerApi cardController;

    public AuthServiceImpl(AuthControllerApi authController, CardControllerApi cardController) {
        this.authController = authController;
        this.cardController = cardController;
    }

    @Override
    public boolean validateTransferToken(String token) throws ApiException {
        return authController.validateToken(token);
    }

    @Override
    public String createSession(SessionRequest sessionRequest) throws ApiException {
        return authController.createSession(sessionRequest);
    }

    @Override
    public AuthMethodeEnum getAuthType(String cardNumber) throws ApiException {
        String authType = cardController.getAuthType(cardNumber);
        return AuthMethodeEnum.valueOf(authType);
    }

    @Override
    public void changeAuthType(ChangeAuthTypeRequest changeAuthTypeRequest) throws ApiException {
        authController.changeAuthSecret(getToken(), changeAuthTypeRequest);
    }

    @Override
    public void closeSession() throws ApiException {
        authController.closeSession(getToken());
    }

    private String getToken() {
        return request.getHeader(TOKEN);
    }
}
