package com.agr.bankservice.api.request;

import com.agr.bankservice.model.enums.CardAuthMethodeEnum;
import lombok.Data;

@Data
public class ChangeAuthTypeRequest {
    private CardAuthMethodeEnum authMethodeEnum;
    private String secret;
}