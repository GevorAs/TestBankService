package com.agr.atmservice.security.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TokenServiceInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    TokenServiceInterceptor tokenServiceInterceptor;
    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui/**",
            "/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/favicon.ico",
            "/error",
            "/api/auth/operation/auth-type",
            "/api/auth/operation/session-token"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenServiceInterceptor).addPathPatterns("/**")
                .excludePathPatterns(AUTH_WHITELIST);
    }
}