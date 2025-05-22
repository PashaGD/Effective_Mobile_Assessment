package com.example.bankcards.util;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.example.bankcards.constants.CripplingConstants.*;

@Component
@RequiredArgsConstructor
public class CardEncryptionUtil {
    @Autowired
    private final Cipher cipher;
    @Autowired
    private final SecretKeySpec keySpec;
    private final Charset charset = StandardCharsets.UTF_8;

    @SneakyThrows
    public String encrypt(String plainText) {
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(charset));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @SneakyThrows
    public String decrypt(String cipherText) {
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, charset);
    }

    @Named("decryptAndMask")
    public String decryptAndMask(String encryptedCardNumber) {
        if (encryptedCardNumber == null)
            return null;

        String decrypted = decrypt(encryptedCardNumber);
        return maskCardNumber(decrypted);
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != CARD_SYMBOL_COUNT) {
            return cardNumber;
        }
        return CARD_MASK + cardNumber.substring(CARD_SYMBOL_REVEAL_POSITION);
    }
}
