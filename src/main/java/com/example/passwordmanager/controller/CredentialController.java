package com.example.passwordmanager.controller;


import com.example.passwordmanager.model.Credential;
import com.example.passwordmanager.repository.CredentialRepository;
import com.example.passwordmanager.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credentials")
@CrossOrigin(origins = "*") // Wichtig für Vue.js Frontend
public class CredentialController {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping
    public List<Credential> getAllCredentials() {
        return credentialRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credential> getCredentialById(@PathVariable Long id) {
        Optional<Credential> credential = credentialRepository.findById(id);
        return credential.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Credential> createCredential(@RequestBody Credential credential) {
        // Passwort verschlüsseln
        String encryptedPassword = encryptionService.encrypt(credential.getPasswordEncrypted());
        credential.setPasswordEncrypted(encryptedPassword);
        credential.setCreatedAt(LocalDateTime.now());

        Credential savedCredential = credentialRepository.save(credential);
        return ResponseEntity.ok(savedCredential);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredential(@PathVariable Long id) {
        if (credentialRepository.existsById(id)) {
            credentialRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //mockdaten
    @GetMapping("/testcredentials")
    public List<Credential> getTestCredentials() {
        Credential credential1 = new Credential("Amazon", "user123", "verschlüsselt123");
        Credential credential2 = new Credential("Netflix", "email@example.com", "verschlüsselt456");

        return List.of(credential1, credential2);
    }

}
