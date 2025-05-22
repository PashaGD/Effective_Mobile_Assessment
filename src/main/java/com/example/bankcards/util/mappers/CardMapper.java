package com.example.bankcards.util.mappers;

import com.example.bankcards.dto.CardCreationDto;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.util.CardEncryptionUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UUIDMapper.class, CardEncryptionUtil.class})
public interface CardMapper {
    @Mapping(source = "id", target = "cardId")
    @Mapping(source = "owner.username", target = "username")
    @Mapping(source = "encryptedCardNumber", target = "number", qualifiedByName = "decryptAndMask")
    CardDto toCardDto(Card card);
}
