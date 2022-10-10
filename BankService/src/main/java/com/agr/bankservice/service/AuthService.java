package com.agr.bankservice.service;

import com.agr.bankservice.api.request.ChangeAuthTypeRequest;
import com.agr.bankservice.api.request.SessionRequest;

public interface AuthService {
    String TOKEN = "TOKEN";

    boolean validateToken(String token);


    void closeSession(String token);

    String saveSession(SessionRequest sessionRequest);

    void changeAuthSecret(String token, ChangeAuthTypeRequest changeAuthTypeRequest);
}
