package com.agr.bankservice.model.enums;

import lombok.Getter;

public enum CardAuthMethodeEnum {
    PIN("PIN"),
    FINGERPRINT("FINGERPRINT");

    @Getter
    public final String name;

    CardAuthMethodeEnum(String name) {
        this.name = name;
    }

    public static CardAuthMethodeEnum getName(String level) {
        CardAuthMethodeEnum[] values = CardAuthMethodeEnum.values();
        for (CardAuthMethodeEnum value : values) {
            if (value.getName().equalsIgnoreCase(level)) {
                return value;
            }
        }
        return null;
    }
}
