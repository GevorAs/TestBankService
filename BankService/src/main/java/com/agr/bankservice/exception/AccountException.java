package com.agr.bankservice.exception;

public class AccountException extends BankException {
    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, String description) {
        super(message, description);
    }
}
