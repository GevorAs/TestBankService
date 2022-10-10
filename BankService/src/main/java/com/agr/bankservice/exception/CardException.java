package com.agr.bankservice.exception;

public class CardException extends BankException {
    public CardException() {
    }

    public CardException(String message) {
        super(message);
    }

    public CardException(String message, String description) {
        super(message, description);
    }
}
