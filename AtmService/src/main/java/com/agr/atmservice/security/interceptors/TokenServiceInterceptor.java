package com.agr.atmservice.security.interceptors;

import com.agr.atmservice.exception.AuthException;
import com.agr.atmservice.service.AuthService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenServiceInterceptor implements HandlerInterceptor {
    public static final String TOKEN = "token";

    private final AuthService authService;

    public TokenServiceInterceptor(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        String token = request.getHeader(TOKEN);
        if (token == null && !authService.validateTransferToken(token)) {
            throw new AuthException("Not Authorized");
        }
        return true;
    }
}