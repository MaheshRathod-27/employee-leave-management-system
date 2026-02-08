package com.company.leave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.leave.model.LeaveApprovalLog;

// Repository for leave approval audit logs.

public interface LeaveApprovalLogRepository
        extends JpaRepository<LeaveApprovalLog, String> {

    List<LeaveApprovalLog> findByLeaveRequestId(String leaveRequestId);
}
