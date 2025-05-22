package com.example.bankcards.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class CryptConfiguration {
    @Value("${card.encryption.transformation}")
    private String algorithm;
    @Value("${card.encryption.key}")
    private String encryptionKey;

    @Bean
    @SneakyThrows
    Cipher cipher() {
        return Cipher.getInstance(algorithm);
    }

    @Bean
    SecretKeySpec secretKeySpec(){
       return new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), algorithm);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
