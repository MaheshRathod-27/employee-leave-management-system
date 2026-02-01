package com.company.leave.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.company.leave.exception.EmployeeNotFoundException;
import com.company.leave.exception.LeaveOverlapException;
import com.company.leave.exception.InvalidLeaveDateException;
import com.company.leave.exception.InsufficientLeaveBalanceException;
import com.company.leave.model.LeaveBalance;
import com.company.leave.model.LeaveRequest;
import com.company.leave.model.LeaveStatus;
import com.company.leave.model.LeaveType;

// All leave-related validations are centralized here.

public class LeaveRequestValidator {

	// Entry point for validating a leave request.

	public void validate(LeaveRequest request, boolean employeeExists, LeaveBalance leaveBalance,
			List<LeaveRequest> existingLeaves) {

		if (!employeeExists) {
			throw new EmployeeNotFoundException(request.getEmployeeId());
		}

		validateDates(request);
		validateLeaveTypeRules(request);
		validateOverlap(request, existingLeaves);
		validateBalance(request, leaveBalance);
	}

	// ---------------- PRIVATE VALIDATION METHODS ----------------

	// Ensures start date is not after end date.
	private void validateDates(LeaveRequest request) {
		if (request.getStartDate().isAfter(request.getEndDate())) {
			throw new InvalidLeaveDateException("Start date cannot be after end date");
		}
	}

	// Applies leave-type specific business rules.
	private void validateLeaveTypeRules(LeaveRequest request) {
		LocalDate today = LocalDate.now();
		LocalDate startDate = request.getStartDate();

		if (request.getLeaveType() == LeaveType.MEDICAL) {
			if (!(startDate.equals(today) || startDate.equals(today.minusDays(1)))) {
				throw new InvalidLeaveDateException("Medical leave can be applied only for today or one day before");
			}
		}

		if (request.getLeaveType() == LeaveType.CASUAL) {
			if (!startDate.isAfter(today)) {
				throw new InvalidLeaveDateException("Casual leave must be applied for a future date");
			}
		}
	}

	// Prevents overlapping leave requests for the same employee
	private void validateOverlap(LeaveRequest request, List<LeaveRequest> existingLeaves) {

		for (LeaveRequest existing : existingLeaves) {

			if (existing.getStatus() != LeaveStatus.APPROVED) {
				continue;
			}

			boolean overlaps = !request.getEndDate().isBefore(existing.getStartDate())
					&& !request.getStartDate().isAfter(existing.getEndDate());

			if (overlaps) {
				throw new LeaveOverlapException("Leave dates overlap with an existing approved leave");
			}
		}
	}

	// Validates whether the employee has sufficient leave balance based on leave
	// type and requested duration
	private void validateBalance(LeaveRequest request, LeaveBalance leaveBalance) {

		int days = (int) ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()) + 1;

		if (request.getLeaveType() == LeaveType.MEDICAL && leaveBalance.getRemainingMedicalLeaves() < days) {

			throw new InsufficientLeaveBalanceException("Insufficient medical leave balance");
		}

		if (request.getLeaveType() == LeaveType.CASUAL && leaveBalance.getRemainingCasualLeaves() < days) {

			throw new InsufficientLeaveBalanceException("Insufficient casual leave balance");
		}
	}
}
