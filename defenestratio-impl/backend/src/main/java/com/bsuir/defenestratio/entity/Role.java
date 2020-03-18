package com.bsuir.defenestratio.entity;

public enum Role {
    ADMIN("admin"),
    CLIENT("client"),
    MENTOR("mentor");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
