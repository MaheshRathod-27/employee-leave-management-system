package com.company.leave.model;

import java.time.LocalDate;

import com.company.leave.exception.LeaveStateException;

/**
 * Represents a leave request raised by an employee.
 * Holds leave details and controls state transitions.
 */
public class LeaveRequest {

    private final String id;
    private final String employeeId;
    private final LeaveType leaveType;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private LeaveStatus status;

    public LeaveRequest(String id,
                        String employeeId,
                        LeaveType leaveType,
                        LocalDate startDate,
                        LocalDate endDate) {

        this.id = id;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    
    //Approves the leave request only when the current state is PENDING.
    public void approve() {
        if (status != LeaveStatus.PENDING) {
            throw new LeaveStateException("Only PENDING leave can be approved");
        }
        this.status = LeaveStatus.APPROVED;
    }

    
   //Rejects the leave request only when the current state is PENDING.
    public void reject() {
        if (status != LeaveStatus.PENDING) {
            throw new LeaveStateException("Only PENDING leave can be rejected");
        }
        this.status = LeaveStatus.REJECTED;
    }
}















