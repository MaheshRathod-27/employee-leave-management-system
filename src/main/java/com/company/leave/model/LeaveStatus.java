package com.company.leave.model;

//Using enum ensures only valid states are allowed.
public enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED,
    CANCELLED
}
