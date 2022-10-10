package com.agr.atmservice.api.controller;

import com.agr.atmservice.api.response.Response;
import com.agr.atmservice.bankservice.ApiException;
import com.agr.atmservice.bankservice.model.CardDto;
import com.agr.atmservice.security.interceptors.TokenServiceInterceptor;
import com.agr.atmservice.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.agr.atmservice.api.response.Api.emptyPositiveResponse;

@RestController
@RequestMapping("api/card/operation")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card")
    public ResponseEntity<CardDto> getCardData(@RequestHeader(name = TokenServiceInterceptor.TOKEN) String token) throws ApiException {
        return ResponseEntity.ok(cardService.getCard());
    }

    @PostMapping("/deposit")
    public ResponseEntity<Response> deposit(@RequestHeader(name = TokenServiceInterceptor.TOKEN) String token,
                                            @RequestParam(value = "amount") BigDecimal amount) throws ApiException {
        cardService.deposit(amount);
        return ResponseEntity.ok(emptyPositiveResponse());
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Response> withdrawal(@RequestHeader(name = TokenServiceInterceptor.TOKEN) String token,
                                               @RequestParam(value = "amount") BigDecimal amount) throws ApiException {
        cardService.withdrawal(amount);
        return ResponseEntity.ok(emptyPositiveResponse());

    }
}
