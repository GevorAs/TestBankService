package com.agr.bankservice.dto;

import lombok.Data;

@Data
public class AccountDto {

    private String accountNumber;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;
}
