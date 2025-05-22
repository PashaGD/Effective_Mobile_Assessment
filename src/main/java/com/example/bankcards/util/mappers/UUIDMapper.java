package com.example.bankcards.util.mappers;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDMapper {
    @Named("uuidToString")
    public String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUuid")
    public UUID stringToUuid(String id) {
        return id != null ? UUID.fromString(id) : null;
    }
}
