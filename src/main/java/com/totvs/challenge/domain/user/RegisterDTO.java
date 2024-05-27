package com.totvs.challenge.domain.user;

public record RegisterDTO(String username, String login, String password, UserRole role) {
}
