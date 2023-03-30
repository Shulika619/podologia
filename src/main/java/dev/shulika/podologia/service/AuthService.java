package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.auth.AuthRequestDTO;
import dev.shulika.podologia.dto.auth.AuthResponseDTO;
import dev.shulika.podologia.dto.auth.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);

    AuthResponseDTO login(AuthRequestDTO authRequestDTO);
}
