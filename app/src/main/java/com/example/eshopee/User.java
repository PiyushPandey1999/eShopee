package com.example.eshopee;

public class User {
    private String[] details;

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

    private static final User user = new User();

    public static User getInstance() {
        return user;
    }
}
