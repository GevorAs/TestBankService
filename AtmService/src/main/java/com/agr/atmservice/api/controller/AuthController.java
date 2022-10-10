package com.agr.atmservice.api.controller;

import com.agr.atmservice.api.response.Response;
import com.agr.atmservice.bankservice.ApiException;
import com.agr.atmservice.bankservice.model.CardDto.AuthMethodeEnum;
import com.agr.atmservice.bankservice.model.ChangeAuthTypeRequest;
import com.agr.atmservice.bankservice.model.SessionRequest;
import com.agr.atmservice.security.interceptors.TokenServiceInterceptor;
import com.agr.atmservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.agr.atmservice.api.response.Api.emptyPositiveResponse;

@RestController
@RequestMapping("api/auth/operation")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/auth-type")
    public ResponseEntity<AuthMethodeEnum> getAuthType(@RequestParam(name = "cardNamber") String cardNumber) throws ApiException {
        return ResponseEntity.ok(authService.getAuthType(cardNumber));
    }

    @PostMapping("/session-token")
    public ResponseEntity<String> createSession(@RequestBody SessionRequest sessionRequest) throws ApiException {
        return ResponseEntity.ok(authService.createSession(sessionRequest));
    }

    @PutMapping(path = "/change-auth-type")
    public ResponseEntity<Response> changeAuthType(@RequestHeader(name = TokenServiceInterceptor.TOKEN) String token,
                                                   @RequestBody ChangeAuthTypeRequest changeAuthTypeRequest) throws ApiException {
        authService.changeAuthType(changeAuthTypeRequest);
        return ResponseEntity.ok(emptyPositiveResponse());
    }

    @PostMapping(path = "/close-session")
    public ResponseEntity<Response> closeSession(@RequestHeader(name = TokenServiceInterceptor.TOKEN) String token) throws ApiException {
        authService.closeSession();
        return ResponseEntity.ok(emptyPositiveResponse());
    }
}
