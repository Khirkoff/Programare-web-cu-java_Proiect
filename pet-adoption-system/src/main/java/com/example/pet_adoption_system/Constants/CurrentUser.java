package com.example.pet_adoption_system.Constants;


public class CurrentUser {

    public static long id;

    public static long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static String role;

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        CurrentUser.role = role;
    }

}
