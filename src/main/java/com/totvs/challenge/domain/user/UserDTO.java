package com.totvs.challenge.domain.user;

public record UserDTO(String login, String password, UserRole role) {
}
