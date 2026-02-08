package com.company.leave.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.leave.exception.LeaveAlreadyProcessedException;
import com.company.leave.exception.InvalidLeaveActionException;
import com.company.leave.model.LeaveApprovalLog;
import com.company.leave.model.LeaveRequest;
import com.company.leave.model.LeaveStatus;
import com.company.leave.repository.LeaveApprovalLogRepository;
import com.company.leave.repository.LeaveRequestRepository;

//Service responsible for handling leave approval workflow.

@Service
public class LeaveApprovalService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveApprovalLogRepository approvalLogRepository;

    public LeaveApprovalService(
            LeaveRequestRepository leaveRequestRepository,
            LeaveApprovalLogRepository approvalLogRepository) {

        this.leaveRequestRepository = leaveRequestRepository;
        this.approvalLogRepository = approvalLogRepository;
    }

    //Approves a leave request.
    
    @Transactional
    public void approveLeave(
            String leaveRequestId,
            String approvedBy,
            String remarks) {

        LeaveRequest leaveRequest = fetchLeaveRequest(leaveRequestId);

        validatePendingState(leaveRequest);

        // Change state using domain logic
        leaveRequest.approve();

        // Create audit log
        LeaveApprovalLog log = new LeaveApprovalLog(
                UUID.randomUUID().toString(),
                leaveRequestId,
                LeaveStatus.APPROVED,
                approvedBy,
                remarks
        );

        // Persist changes atomically
        leaveRequestRepository.save(leaveRequest);
        approvalLogRepository.save(log);
    }

    //Rejects a leave request.
    
    @Transactional
    public void rejectLeave(
            String leaveRequestId,
            String rejectedBy,
            String remarks) {

        LeaveRequest leaveRequest = fetchLeaveRequest(leaveRequestId);

        validatePendingState(leaveRequest);

        // Change state using domain logic
        leaveRequest.reject();

        // Create audit log
        LeaveApprovalLog log = new LeaveApprovalLog(
                UUID.randomUUID().toString(),
                leaveRequestId,
                LeaveStatus.REJECTED,
                rejectedBy,
                remarks
        );

        // Persist changes atomically
        leaveRequestRepository.save(leaveRequest);
        approvalLogRepository.save(log);
    }

    //Fetches leave request or fails fast.
  
    private LeaveRequest fetchLeaveRequest(String leaveRequestId) {
        return leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new InvalidLeaveActionException(
                                "Leave request not found: " + leaveRequestId));
    }

    //Ensures leave is in PENDING state before processing.
   
    private void validatePendingState(LeaveRequest leaveRequest) {

        if (leaveRequest.getStatus() == LeaveStatus.APPROVED) {
            throw new LeaveAlreadyProcessedException(
                    "Leave request is already approved");
        }

        if (leaveRequest.getStatus() == LeaveStatus.REJECTED) {
            throw new LeaveAlreadyProcessedException(
                    "Leave request is already rejected");
        }

        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new InvalidLeaveActionException(
                    "Invalid leave state for approval workflow");
        }
    }
}
