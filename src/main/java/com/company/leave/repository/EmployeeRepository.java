package com.company.leave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.leave.model.Employee;

//Used to check the employee in present in the db or not
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
