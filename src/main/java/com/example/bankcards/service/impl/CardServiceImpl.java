package com.example.bankcards.service.impl;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatus;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.exceptions.CardAlreadyBlockedException;
import com.example.bankcards.exception.exceptions.CardNotFoundException;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.service.CardService;
import com.example.bankcards.service.UserControlService;
import com.example.bankcards.util.CardServiceValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    @Autowired
    private final CardRepository repository;

    @Autowired
    private final UserControlService userControlService;

    @Autowired
    private final CardServiceValidator validator;

    @Override
    @Transactional
    public Card createCard(Card cardDto) {
        return repository.save(cardDto);
    }

    @Override
    @Transactional
    public Card blockCard(UUID uuid) {
        Card card = getCardById(uuid);
        validator.validateCardStatus(card, CardStatus.BLOCKED, false, "Карта уже заблокирована");

        card.setStatus(CardStatus.BLOCKED);
        return repository.save(card);
    }

    @Override
    public Card activateCard(UUID uuid) {
        Card card = getCardById(uuid);
        validator.validateCardStatus(card, CardStatus.ACTIVATED, false, "Карта уже активирована");

        card.setStatus(CardStatus.ACTIVATED);
        return repository.save(card);
    }

    @Override
    @Transactional
    public Card disableCard(UUID cardId) {
        Card card = getCardById(cardId);

        if (CardStatus.EXPIRED.equals(card.getStatus()))
            throw new CardAlreadyBlockedException("Карта уже отключена");

        card.setStatus(CardStatus.EXPIRED);
        return repository.save(card);
    }

    @Override
    public Card getCardById(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new CardNotFoundException("Карта не была найдена"));
    }

    @Override
    public void deleteCard(UUID uuid) {
        Card card = getCardById(uuid);
        validator.validateCardStatus(card, CardStatus.EXPIRED, false,  "Карта eщё не может быть удалена");
        repository.delete(card);
    }

    @Override
    public void removeCardToUser(UUID cardId, UUID userId) {
        Card card = getCardByIdAndUserId(cardId, userId);
        repository.delete(card);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal checkBalance(UUID userId, UUID cardId){
        return repository.findBalanceByCardIdAndUserId(cardId, userId);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Card> getUserCards(UUID uuid, Pageable pageable){
        User user = userControlService.findUser(uuid);
        return repository.findAllByOwner(user, pageable);
    }

    @Override
    public Page<Card> getAllUserCards(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Card getCardByIdAndUserId(UUID cardId, UUID userId){
        return repository.findByIdAndOwnerId(cardId, userId)
                .orElseThrow(() -> new CardNotFoundException("Карта пользователя не найдена"));
    }
}
