package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.auth.AuthResponseDTO;
import dev.shulika.podologia.dto.auth.RegisterRequestDTO;
import dev.shulika.podologia.model.User;

public class UserMapper {

    public static AuthResponseDTO toDTO(User user) {
        AuthResponseDTO registerResponseDTO = new AuthResponseDTO();
        registerResponseDTO.setId(user.getId());
        registerResponseDTO.setFirstname(user.getFirstname());
        registerResponseDTO.setLastname(user.getLastname());
        registerResponseDTO.setEmail(user.getEmail());
        registerResponseDTO.setRole(user.getRole());
        registerResponseDTO.setEnabled(user.getEnabled());
        registerResponseDTO.setCreatedAt(user.getCreatedAt());
        return registerResponseDTO;
    }

    public static User fromRegistrationDTO(RegisterRequestDTO registerRequestDTO) {
        User user = new User();
        user.setFirstname(registerRequestDTO.getFirstname());
        user.setLastname(registerRequestDTO.getLastname());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(registerRequestDTO.getPassword());
        user.setRole("USER");
        user.setEnabled(true);
        return user;
    }
}
