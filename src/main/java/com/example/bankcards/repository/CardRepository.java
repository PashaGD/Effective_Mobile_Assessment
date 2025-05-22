package com.example.bankcards.repository;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    Page<Card> findAllByOwner(User owner, Pageable pageable);
    Set<Card> findSetByIdInAndOwnerId(List<UUID> cardIds, UUID ownerId);
    Optional<Card> findByIdAndOwnerId(UUID cardIds, UUID ownerId);
    @Query("SELECT c.balance FROM Card c WHERE c.id = :cardId AND c.owner.id = :userId")
    BigDecimal findBalanceByCardIdAndUserId(@Param("cardId") UUID cardId, @Param("userId") UUID userId);

}
