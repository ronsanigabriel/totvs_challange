package com.totvs.challenge.controllers;

import com.totvs.challenge.domain.user.RegisterDTO;
import com.totvs.challenge.domain.user.User;
import com.totvs.challenge.domain.user.UserResponseDTO;
import com.totvs.challenge.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getUserByLogin")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (this.userRepository.findByLogin(registerDTO.login()) == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new UserResponseDTO((User) this.userRepository.findByLogin(registerDTO.login())));
    }



}
