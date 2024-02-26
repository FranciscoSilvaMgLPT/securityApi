package com.example.security.dto;

import com.example.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Role role;
}
