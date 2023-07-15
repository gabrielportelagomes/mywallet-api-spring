package com.api.mywallet.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @JsonCreator
    public static UserRole fromString(String value) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(value)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
