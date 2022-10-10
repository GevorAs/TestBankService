package com.agr.bankservice.api.controler;

import com.agr.bankservice.api.request.ChangeAuthTypeRequest;
import com.agr.bankservice.api.request.SessionRequest;
import com.agr.bankservice.api.response.EmptyPositiveResponse;
import com.agr.bankservice.api.response.Response;
import com.agr.bankservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(path = "/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader(name = AuthService.TOKEN) String token) {
        boolean isValid = authService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping(path = "/create-session")
    public ResponseEntity<String> createSession(@RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(authService.saveSession(sessionRequest));
    }

    @PostMapping(path = "/close-session")
    public ResponseEntity<Response> closeSession(@RequestHeader(name = AuthService.TOKEN) String token) {
        authService.closeSession(token);
        return ResponseEntity.ok(new EmptyPositiveResponse());
    }

    @PutMapping(path = "/change-auth-secret")
    public ResponseEntity<String> changeAuthSecret(@RequestHeader(name = AuthService.TOKEN) String token,
                                                   @RequestBody ChangeAuthTypeRequest changeAuthTypeRequest) {
        authService.changeAuthSecret(token, changeAuthTypeRequest);
        return ResponseEntity.ok(changeAuthTypeRequest.getSecret());
    }
}
