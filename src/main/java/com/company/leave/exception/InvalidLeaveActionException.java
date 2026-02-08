package com.company.leave.exception;

//Thrown when an invalid approval action is attempted.

public class InvalidLeaveActionException extends RuntimeException {

    public InvalidLeaveActionException(String message) {
        super(message);
    }
}
