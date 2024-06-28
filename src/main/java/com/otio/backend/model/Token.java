package com.otio.backend.model;

import java.time.LocalDateTime;

// Use cases:
    // An activity being saved by a user
    // (Prospective) looking at an account

public class Token {
    private String token;
    private LocalDateTime timeout;
    
    public Token() {}

    public Token(String token, LocalDateTime timeout) {
        this.token = token;
        this.timeout = timeout;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTimeout() {
        return timeout;
    }

    public void setTimeout(LocalDateTime timeout) {
        this.timeout = timeout;
    }
}
