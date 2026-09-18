package com.banking.api.exceptions;

import com.banking.api.enums.PixKeyType;

public class NotInsertKayValueExceptions extends RuntimeException {
    private final String keyValue;
    private final PixKeyType type;

    public NotInsertKayValueExceptions(String message, String keyValue, PixKeyType type)
    {
        super(message);
        this.keyValue = keyValue;
        this.type = type;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public PixKeyType getType() {
        return type;
    }
}
