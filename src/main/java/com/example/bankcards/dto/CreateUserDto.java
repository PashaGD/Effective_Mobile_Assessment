package com.example.bankcards.dto;

import com.example.bankcards.entity.Role;

import java.util.Set;
import java.util.UUID;

public record CreateUserDto(UUID name, String password, Set<Role> roles) {
}
