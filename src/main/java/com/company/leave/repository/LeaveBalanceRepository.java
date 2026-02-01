package com.company.leave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.leave.model.LeaveBalance;


 // Used to fetch leave balance of an employee.

public interface LeaveBalanceRepository
        extends JpaRepository<LeaveBalance, String> {
}
