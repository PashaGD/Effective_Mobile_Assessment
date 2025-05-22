package com.example.bankcards.util;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatus;
import com.example.bankcards.exception.exceptions.CardAlreadyBlockedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardServiceValidator {
    public void validateCardStatus(Card card, CardStatus status,
                                   boolean isStatusesEqual, String errorMessage){
        if(status.equals(card.getStatus()) != isStatusesEqual)
            throw new CardAlreadyBlockedException(errorMessage);
    }
}
