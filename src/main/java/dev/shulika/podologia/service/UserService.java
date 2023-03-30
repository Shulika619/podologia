package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.user.UserRequestDTO;
import dev.shulika.podologia.dto.user.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserService {
    Page<UserResponseDTO> findAll(Pageable pageable);

    UserResponseDTO findById(Long id);

    UserResponseDTO create(UserRequestDTO user);

    UserResponseDTO update(Long id, UserRequestDTO user);

    UserResponseDTO updateFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}
