package com.company.leave.exception;

// Thrown when a leave overlaps with an existing approved leave.
 
public class LeaveOverlapException extends RuntimeException {

    public LeaveOverlapException(String message) {
        super(message);
    }
}