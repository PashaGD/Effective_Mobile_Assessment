package com.example.bankcards.dto;

import com.example.bankcards.entity.CardStatus;

import java.util.UUID;

public record ChangeCardStatusDto (UUID cardId, CardStatus status) {
}
