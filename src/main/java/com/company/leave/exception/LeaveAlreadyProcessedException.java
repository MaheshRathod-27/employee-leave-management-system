package com.company.leave.exception;

// Thrown when an approval or rejection is attempted on an already processed leave request.
public class LeaveAlreadyProcessedException extends RuntimeException {

    public LeaveAlreadyProcessedException(String message) {
        super(message);
    }
}
