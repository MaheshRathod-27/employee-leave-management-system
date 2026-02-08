package com.company.leave.model;

import java.time.LocalDateTime;

// Audit log for leave approval actions,Each record represents a single approval or rejection event.
 
public class LeaveApprovalLog {

    private final String id;
    private final String leaveRequestId;
    private final LeaveStatus action;
    private final String actionBy;
    private final LocalDateTime actionAt;
    private final String remarks;

    public LeaveApprovalLog(String id,String leaveRequestId,LeaveStatus action,String actionBy,String remarks) {
        this.id = id;
        this.leaveRequestId = leaveRequestId;
        this.action = action;
        this.actionBy = actionBy;
        this.actionAt = LocalDateTime.now();
        this.remarks = remarks;
    }

    public String getLeaveRequestId() {
        return leaveRequestId;
    }

    public LeaveStatus getAction() {
        return action;
    }

    public String getActionBy() {
        return actionBy;
    }

    public LocalDateTime getActionAt() {
        return actionAt;
    }

    public String getRemarks() {
        return remarks;
    }
}
