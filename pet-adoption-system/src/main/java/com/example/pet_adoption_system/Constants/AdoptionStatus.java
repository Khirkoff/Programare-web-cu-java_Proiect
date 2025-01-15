package com.example.pet_adoption_system.Constants;

public class AdoptionStatus {
    public static final String PENDING = "Pending";
    public static final String APPROVED = "Approved";
    public static final String DECLINED = "Declined";


    public static boolean isValid(String status) {
        return status.equals(PENDING) ||
                status.equals(APPROVED) ||
                status.equals(DECLINED);
    }
}
