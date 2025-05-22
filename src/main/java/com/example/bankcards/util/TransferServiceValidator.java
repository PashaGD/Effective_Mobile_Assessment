package com.example.bankcards.util;

import com.example.bankcards.entity.Card;
import com.example.bankcards.exception.exceptions.BalanceNotEnoughException;
import com.example.bankcards.exception.exceptions.CardNotFoundException;
import com.example.bankcards.exception.exceptions.NotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static com.example.bankcards.constants.FoundTransferringConstants.NOT_ENOUGH_CARDS;
import static com.example.bankcards.constants.FoundTransferringConstants.NOT_ENOUGH_MONEY;

@Component
public class TransferServiceValidator {
    public Card getCardByUUID(Set<Card> cardSet, UUID uuid, String errorMessage) {
        return cardSet.stream()
                .filter(card -> card.getId().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new CardNotFoundException(errorMessage));
    }

    public void checkCartBalance(Card card, BigDecimal fund){
        if (card.getBalance().compareTo(fund) < 0) {
            throw new BalanceNotEnoughException(NOT_ENOUGH_MONEY);
        }
    }

    public void checkCardCountBuggerThan(Set<Card> cardSet, int size){
        if (cardSet.size() < size)
            throw new CardNotFoundException(NOT_ENOUGH_CARDS);
    }
}
