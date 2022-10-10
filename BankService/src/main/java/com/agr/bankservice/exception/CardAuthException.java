package com.agr.bankservice.exception;

public class CardAuthException extends BankException {

    public CardAuthException() {
    }

    public CardAuthException(String message) {
        super(message);
    }

    public CardAuthException(String message, String description) {
        super(message, description);
    }
}
