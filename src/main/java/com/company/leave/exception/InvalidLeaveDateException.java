package com.company.leave.exception;

//Thrown when leave date rules are violated.

public class InvalidLeaveDateException extends RuntimeException {

    public InvalidLeaveDateException(String message) {
        super(message);
    }
}