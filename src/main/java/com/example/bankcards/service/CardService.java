package com.example.bankcards.service;

import com.example.bankcards.dto.CardCreationDto;
import com.example.bankcards.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.math.BigDecimal;
import java.util.UUID;

public interface CardService {
    Card createCard(Card cardDto);
    Card blockCard(UUID uuid);
    Card activateCard(UUID uuid);
    Card disableCard(UUID uuid);
    Card getCardById(UUID uuid);
    BigDecimal checkBalance(UUID userId, UUID cardId);

    void deleteCard(UUID uuid);
    void removeCardToUser(UUID cardId, UUID userId);

    /**
     *
     * @param uuid ID пользователя
     * @param pageable страничка для пагинации
     * @return страницу карт, попавших на данную страничку
     *
     * Возвращает пользователю с ролью USER все его карты
     */
    Page<Card> getUserCards(UUID uuid, Pageable pageable);

    /**
     *
     * @param pageable страничка для пагинации
     * @return страницу карт, попавших на данную страничку
     *
     *  * Возвращает администратору все карты в базе
     */
    Page<Card> getAllUserCards(Pageable pageable);
}
