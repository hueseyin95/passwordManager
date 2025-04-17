package com.example.passwordmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credentials")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String service;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, name = "password_encrypted")
    private String passwordEncrypted;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Credential() {
        // Standard Konstruktor
    }

    public Credential(String service, String username, String passwordEncrypted) {
        this.service = service;
        this.username = username;
        this.passwordEncrypted = passwordEncrypted;
        this.createdAt = LocalDateTime.now();
    }

    // Getter und Setter
    public Long getId() { return id; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordEncrypted() { return passwordEncrypted; }
    public void setPasswordEncrypted(String passwordEncrypted) { this.passwordEncrypted = passwordEncrypted; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
