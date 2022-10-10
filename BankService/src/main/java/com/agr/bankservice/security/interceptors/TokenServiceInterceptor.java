package com.agr.bankservice.security.interceptors;

import com.agr.bankservice.exception.AuthException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;


// check requested service access
@Service
public class TokenServiceInterceptor implements HandlerInterceptor {
    public static final String SERVICE_TOKEN = "SERVICE_TOKEN";
    @Value("${atm.service.token}")
    private String servicesToken;

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        String token = request.getHeader(SERVICE_TOKEN);
        if (!servicesToken.equals(token)) {
            throw new AuthException("Not Authorized service");
        }
        return true;
    }
}