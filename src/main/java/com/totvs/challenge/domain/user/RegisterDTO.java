package com.totvs.challenge.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
