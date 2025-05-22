package com.example.bankcards.dto;

import java.util.UUID;

public record CardDto(UUID cardId, String number, String username) {
}
