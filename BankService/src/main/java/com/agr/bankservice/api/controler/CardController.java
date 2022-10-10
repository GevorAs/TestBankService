package com.agr.bankservice.api.controler;

import com.agr.bankservice.api.response.EmptyPositiveResponse;
import com.agr.bankservice.api.response.Response;
import com.agr.bankservice.dto.CardDto;
import com.agr.bankservice.model.enums.CardAuthMethodeEnum;
import com.agr.bankservice.service.AuthService;
import com.agr.bankservice.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

// todo CRUD api for card
@RestController
@RequestMapping("api/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping(path = "/get-auth-type")
    public ResponseEntity<CardAuthMethodeEnum> getAuthType(@RequestParam(name = "cardNumber") String cardNumber) {
        return ResponseEntity.ok(cardService.getAuthType(cardNumber));
    }

    @GetMapping(path = "/card-data")
    public ResponseEntity<CardDto> getCard(@RequestHeader(name = AuthService.TOKEN) String token) {
        return ResponseEntity.ok(cardService.getCard(token));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Response> deposit(@RequestHeader(name = AuthService.TOKEN) String token,
                                            @RequestParam(value = "amount") BigDecimal amount) {
        cardService.deposit(amount, token);
        return ResponseEntity.ok(new EmptyPositiveResponse());
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Response> withdrawal(@RequestHeader(name = AuthService.TOKEN) String token,
                                               @RequestParam(value = "amount") BigDecimal amount) {
        cardService.withdrawal(amount, token);
        return ResponseEntity.ok(new EmptyPositiveResponse());
    }
}
