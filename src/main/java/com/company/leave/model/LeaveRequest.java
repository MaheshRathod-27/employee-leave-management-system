package com.company.leave.model;

import java.time.LocalDate;

import com.company.leave.exception.LeaveStateException;

public class LeaveRequest {

    private final String id;
    private final String employeeId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private LeaveStatus status;

    public LeaveRequest(String id, String employeeId,LocalDate startDate, LocalDate endDate) {

       
        this.id = id;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void approve() {
    	// Prevent approving a leave more than once
        if (status != LeaveStatus.PENDING) {
            throw new LeaveStateException("Only PENDING leave can be approved");
        }
        this.status = LeaveStatus.APPROVED;
    }

    public void reject() {
    	// Rejecting is allowed only when leave is still pending
        if (status != LeaveStatus.PENDING) {
            throw new LeaveStateException("Only PENDING leave can be rejected");
        }
        this.status = LeaveStatus.REJECTED;
    }
}
