package com.example.bankcards.service;

import com.example.bankcards.entity.User;
import java.util.UUID;


public interface UserControlService {
    User findUser(UUID uuid);
    User createUser(User dto);
    User updateUser(User dto, UUID id);

    void deleteUser(UUID id);
}
