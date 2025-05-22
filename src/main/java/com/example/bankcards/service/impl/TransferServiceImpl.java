package com.example.bankcards.service.impl;

import static com.example.bankcards.constants.FoundTransferringConstants.FROM_CARD_NOT_FOUND;
import static com.example.bankcards.constants.FoundTransferringConstants.TO_CARD_NOT_FOUND;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

import com.example.bankcards.dto.TransferFunds;
import com.example.bankcards.entity.Card;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.service.TransferService;
import com.example.bankcards.util.TransferServiceValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final CardRepository repository;
    private final TransferServiceValidator validator;

    @Transactional(isolation = REPEATABLE_READ)
    @Override
    public void transferFunds(UUID userId, TransferFunds dto) {
        Set<Card> cards = repository.findSetByIdInAndOwnerId(
                List.of(dto.fromCardId(), dto.toCardId()), userId);

        validator.checkCardCountBuggerThan(cards, 2);
        Card fromCard = validator.getCardByUUID(cards, dto.fromCardId(), FROM_CARD_NOT_FOUND);
        Card toCard = validator.getCardByUUID(cards, dto.toCardId(), TO_CARD_NOT_FOUND);
        validator.checkCartBalance(fromCard, dto.amount());

        fromCard.setBalance(fromCard.getBalance().subtract(dto.amount()));
        toCard.setBalance(toCard.getBalance().add(dto.amount()));

        repository.save(fromCard);
        repository.save(toCard);
    }
}
