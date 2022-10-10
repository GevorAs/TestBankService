package com.agr.bankservice.exception;

import lombok.Getter;

public class BankException extends RuntimeException {

    @Getter
    private String description;

    public BankException() {
        super("The Bank error");
    }

    public BankException(String message) {
        super(message);
    }

    public BankException(String message, String description) {
        super(message);
        this.description = description;
    }
}
