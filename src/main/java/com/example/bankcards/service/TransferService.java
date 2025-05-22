package com.example.bankcards.service;

import com.example.bankcards.dto.TransferFunds;

import java.util.UUID;

public interface TransferService {
    void transferFunds(UUID userId, TransferFunds dto);
}
