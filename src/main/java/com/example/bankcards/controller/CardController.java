package com.example.bankcards.controller;

import com.example.bankcards.dto.CardCreationDto;
import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.ChangeCardStatusDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/effective_mobile/cards")
public interface CardController {
    @PostMapping("/")
    ResponseEntity<CardDto> createCardToUser(@RequestBody CardCreationDto cardDto);
    @GetMapping("/{cardId}")
    ResponseEntity<CardDto> getUserCard(@PathVariable UUID cardId);
    @PatchMapping("/")
    ResponseEntity<CardDto> changeCardStatus(@PathVariable ChangeCardStatusDto dto);
    @DeleteMapping("/{cardId}")
    ResponseEntity<Void> deleteCard(@PathVariable UUID cardId);
    @GetMapping("/cards")
    ResponseEntity<Void> getUsersCards(@RequestParam UUID userId);

}
