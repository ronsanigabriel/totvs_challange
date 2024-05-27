package com.totvs.challenge.domain.user;

import org.springframework.security.core.userdetails.UserDetails;

public record UserResponseDTO(String id, String login) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getLogin());
    }
}
