package com.banking.api.exceptions;

import com.banking.api.enums.TransferType;

public class TransferAccountExceptions extends RuntimeException {
    private final TransferType type;
    private final Long destinationId;
    private final Long sourceId;
    public TransferAccountExceptions(String message, TransferType type, Long destinationId, Long sourceId ) {
        super(message);
        this.destinationId = destinationId;
        this.type = type;
        this.sourceId = sourceId;
    }

    public TransferType getType() {
        return type;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public Long getSourceId() {
        return sourceId;
    }
}
