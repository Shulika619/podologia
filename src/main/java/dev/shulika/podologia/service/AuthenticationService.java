package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.auth.AuthRequestDTO;
import dev.shulika.podologia.dto.auth.AuthResponseDTO;
import dev.shulika.podologia.dto.auth.RegisterRequestDTO;
import dev.shulika.podologia.model.User;

public interface AuthenticationService {
    AuthResponseDTO register(RegisterRequestDTO request);

    AuthResponseDTO authenticate(AuthRequestDTO request);

    void saveUserToken(User user, String jwtToken);

    void revokeAllUserTokens(User user);
}
