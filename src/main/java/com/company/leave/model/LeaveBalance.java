package com.company.leave.model;

//Tracks leave balance for an employee.
public class LeaveBalance {

    private static final int TOTAL_LEAVES = 24;
    
    //We used final because these values should never change once the leave balance is created
    private final String employeeId;
    private final int medicalLeaves;
    private final int casualLeaves;
    private int usedMedicalLeaves;
    private int usedCasualLeaves;
    
    
    //The constructor is used when the leave balance is created for the first time.
    public LeaveBalance(String employeeId) {
        this.employeeId = employeeId;
        this.medicalLeaves = (int) (TOTAL_LEAVES * 0.4);//40% leave for the medicalLeaves
        this.casualLeaves = TOTAL_LEAVES - this.medicalLeaves;//60% for casual leaves

        this.usedMedicalLeaves = 0;
        this.usedCasualLeaves = 0;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public int getRemainingMedicalLeaves() {
        return medicalLeaves - usedMedicalLeaves;
    }

    public int getRemainingCasualLeaves() {
        return casualLeaves - usedCasualLeaves;
    }

    //Deducts leave after approval.
    public void deductLeaves(LeaveType leaveType, int days) {
        if (leaveType == LeaveType.MEDICAL) {
            this.usedMedicalLeaves += days;
        } else if (leaveType == LeaveType.CASUAL) {
            this.usedCasualLeaves += days;
        }
    }
}
