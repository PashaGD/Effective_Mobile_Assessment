package com.example.bankcards.facade;

import com.example.bankcards.dto.CardCreationDto;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatus;
import com.example.bankcards.entity.User;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserControlService;
import com.example.bankcards.util.CardEncryptionUtil;
import com.example.bankcards.util.mappers.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CardFacade {
    private final CardService cardService;
    private final UserControlService userControlService;
    private final CardEncryptionUtil cardEncryptor;
    private final CardMapper mapper;

    public ResponseEntity<CardDto> createCardToUser(CardCreationDto cardDto) {
        String encryptedNumber = cardEncryptor.encrypt(cardDto.cardNumber());
        User owner = userControlService.findUser(cardDto.userId());
        LocalDate date = LocalDate.of(cardDto.year(), cardDto.month(), cardDto.day());
        CardStatus status = CardStatus.ACTIVATED;

        Card card = Card.builder()
                .encryptedCardNumber(encryptedNumber)
                .owner(owner)
                .expirationDate(date)
                .status(status)
                .build();

        var result = cardService.createCard(card);
        var mappedDto = mapper.toCardDto(result);

        return ResponseEntity.ok(mappedDto);
    }

    public ResponseEntity<CardDto> getUserCard(UUID cardId) {
        return null;
    }
}
