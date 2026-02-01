package com.company.leave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.leave.model.LeaveRequest;


 //Used to fetch leave history of an employee.
public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, String> {

    List<LeaveRequest> findByEmployeeId(String employeeId);
}
