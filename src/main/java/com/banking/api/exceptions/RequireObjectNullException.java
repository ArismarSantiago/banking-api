package com.banking.api.exceptions;

public class RequireObjectNullException extends RuntimeException {

    public RequireObjectNullException(){
        super("It is not allowed to persist a null object");
    }
    public RequireObjectNullException(String message) {
        super(message);
    }
}
