package com.example.bankcards.controller.impl;

import com.example.bankcards.controller.CardController;
import com.example.bankcards.dto.CardCreationDto;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.ChangeCardStatusDto;
import com.example.bankcards.facade.CardFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CardControllerImpl implements CardController {
    private final CardFacade cardFacade;

    @Override
    public ResponseEntity<CardDto> createCardToUser(CardCreationDto cardDto) {
        return cardFacade.createCardToUser(cardDto);
    }

    @Override
    public ResponseEntity<CardDto> getUserCard(UUID cardId) {
        return cardFacade.getUserCard(cardId);
    }

    @Override
    public ResponseEntity<CardDto> changeCardStatus(ChangeCardStatusDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteCard(UUID cardId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> getUsersCards(UUID userId) {
        return null;
    }
}
