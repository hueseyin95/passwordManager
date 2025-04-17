package com.example.passwordmanager.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class EncryptionService {

    // Dummy-Verschlüsselung für Demo-Zwecke (Base64 Encoding)
    public String encrypt(String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }

    public String decrypt(String encryptedText) {
        return new String(Base64.getDecoder().decode(encryptedText));
    }
}
