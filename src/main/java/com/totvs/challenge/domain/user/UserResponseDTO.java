package com.totvs.challenge.domain.user;

public record UserResponseDTO(String id, String login) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getLogin());
    }
}
