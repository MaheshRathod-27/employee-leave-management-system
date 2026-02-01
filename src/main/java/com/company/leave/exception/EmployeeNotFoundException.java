package com.company.leave.exception;

// Exception to be used in service layer when an employee is not found.
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String employeeId) {
        super("Employee not found with id: " + employeeId);
    }
}
