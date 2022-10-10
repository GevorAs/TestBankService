package com.agr.bankservice.exception;

public class AuthException extends BankException {

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, String description) {
        super(message, description);
    }
}
