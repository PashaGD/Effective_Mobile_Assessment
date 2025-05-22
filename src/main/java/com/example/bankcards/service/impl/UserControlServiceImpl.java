package com.example.bankcards.service.impl;

import com.example.bankcards.entity.User;
import com.example.bankcards.exception.exceptions.UserAlreadyExistException;
import com.example.bankcards.exception.exceptions.UserNotExistException;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.service.UserControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserControlServiceImpl implements UserControlService {
    private final UserRepository repository;

    @Override
    public User findUser(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new UserNotExistException("Пользователь не найден"));
    }

    @Override
    public User createUser(User user) {
       return repository.save(user);
    }

    @Override
    public User updateUser(User dto, UUID id) {
        User user = findUser(id);
        repository.findByUsername(dto.getName())
                .orElseThrow(() -> new UserAlreadyExistException("Пользователь с таким именем уже существует"));

        user.setName(dto.getName());
        user.setEncryptedPassword(dto.getEncryptedPassword());
        user.setRoles(dto.getRoles());

        return repository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        User user = findUser(id);
        repository.delete(user);
    }
}
