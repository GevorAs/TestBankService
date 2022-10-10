package com.agr.atmservice.config;

import com.agr.atmservice.bankservice.ApiClient;
import com.agr.atmservice.bankservice.api.AccountControllerApi;
import com.agr.atmservice.bankservice.api.AuthControllerApi;
import com.agr.atmservice.bankservice.api.CardControllerApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BankServiceApiConfig {
    public static final String SERVICE_TOKEN = "SERVICE_TOKEN";
    @Value("${bankService.base.url}")
    String bankServiceBaseUrl;
    @Value("${atm.service.token}")
    private String servicesToken;

    @Bean
    public ApiClient bankServiceApiClient() {
        ApiClient client = new ApiClient();

        if (StringUtils.isNotBlank(bankServiceBaseUrl)) {
            client.setBasePath(bankServiceBaseUrl);
        }
        client.setHttpClient(
                client.getHttpClient()
                        .newBuilder()
                        .build()
        );
        client.addDefaultHeader(SERVICE_TOKEN, servicesToken);
        return client;
    }

    public BankServiceApiConfig() {

    }

    @Bean
    public AccountControllerApi accountControllerApi() {
        return new AccountControllerApi(bankServiceApiClient());
    }

    @Bean
    public CardControllerApi cardControllerApi() {
        return new CardControllerApi(bankServiceApiClient());
    }

    @Bean
    public AuthControllerApi authControllerApi() {
        return new AuthControllerApi(bankServiceApiClient());
    }
}

