package com.company.leave.exception;

// Thrown when employee does not have enough leave balance.
 
public class InsufficientLeaveBalanceException extends RuntimeException {

    public InsufficientLeaveBalanceException(String message) {
        super(message);
    }
}