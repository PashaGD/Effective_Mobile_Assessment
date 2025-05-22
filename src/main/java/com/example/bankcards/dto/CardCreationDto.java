package com.example.bankcards.dto;

import java.util.UUID;

public record CardCreationDto(String cardNumber, UUID userId, int year, int month, int day) {
}
