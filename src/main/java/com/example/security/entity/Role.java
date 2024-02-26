package com.example.security.entity;

import lombok.Getter;

@Getter
public enum Role {
    GUEST,
    USER,
    ADMIN,
    HEADMASTER;

    Role() {
    }
}
