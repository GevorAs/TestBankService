package com.agr.bankservice.api.controler;

import com.agr.bankservice.dto.AccountDto;
import com.agr.bankservice.service.AccountService;
import com.agr.bankservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//todo CRUD api for account
@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/account-data")
    public ResponseEntity<AccountDto> getAccountData(@RequestHeader(name = AuthService.TOKEN) String token) {
        return ResponseEntity.ok(accountService.getAccount());
    }
}
