package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.auth.AuthRequestDTO;
import dev.shulika.podologia.dto.auth.AuthResponseDTO;
import dev.shulika.podologia.dto.auth.RegisterRequestDTO;
import dev.shulika.podologia.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody @Valid RegisterRequestDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(
            @RequestBody @Valid AuthRequestDTO request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
