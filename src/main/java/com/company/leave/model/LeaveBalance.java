package com.company.leave.model;

//Tracks leave balance for an employee.
public class LeaveBalance {

    private final String employeeId;
    private int totalLeaves;
    private int usedLeaves;

    public LeaveBalance(String employeeId, int totalLeaves) {

        this.employeeId = employeeId;
        this.totalLeaves = totalLeaves;
        this.usedLeaves = 0;
    }

    public int getRemainingLeaves() {
        return totalLeaves - usedLeaves;
    }

    public void deductLeaves(int days) {
        
        this.usedLeaves += days;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public int getUsedLeaves() {
        return usedLeaves;
    }
}
