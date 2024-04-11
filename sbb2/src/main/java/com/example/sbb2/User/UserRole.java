package com.example.sbb2.User;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole (String Value) {
        this.Value = Value;
    }

    private final String Value;
}
