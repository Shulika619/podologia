package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.user.UserRequestDTO;
import dev.shulika.podologia.dto.user.UserResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.Role;
import dev.shulika.podologia.model.User;
import dev.shulika.podologia.repository.UserRepository;
import dev.shulika.podologia.service.UserService;
import dev.shulika.podologia.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        log.info("IN UserServiceImpl - findAll - STARTED");
        Page<User> userPages = userRepository.findAll(pageable);
        log.info("IN UserServiceImpl - findAll - FINISHED SUCCESSFULLY - UserMapper::toDTO NOW");
        return userPages.map(UserMapper::toDTO);
    }

    @Override
    public UserResponseDTO findById(Long id) {
        log.info("IN UserServiceImpl - findById: {} - STARTED", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "User not found with id: " + id));
        log.info("IN UserServiceImpl - findById: {} - FINISHED SUCCESSFULLY - UserMapper.toDTO NOW", id);
        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO create(UserRequestDTO user) {
        log.info("IN UserServiceImpl - create: STARTED");
        if (userRepository.existsByEmail(user.getEmail()))
            throw new ServiceBusinessException(user.getEmail(), "A user with this email already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userReturned = userRepository.save(UserMapper.fromDTO(user));
        log.info("IN UserServiceImpl - created - FINISHED SUCCESSFULLY");
        return UserMapper.toDTO(userReturned);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
        log.info("IN UserServiceImpl - update user by id: {} - STARTED", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "User not found with id: " + id));
        user.setFirstname(userRequestDTO.getFirstname());
        user.setLastname(userRequestDTO.getLastname());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(userRequestDTO.getRole());
        user.setEnabled(userRequestDTO.getEnabled());
        User userReturned = userRepository.save(user);
        log.info("IN UserServiceImpl - update user by id: {} - FINISHED SUCCESSFULLY", id);
        return UserMapper.toDTO(userReturned);
    }

    @Override
    public UserResponseDTO updateFields(Long id, Map<String, Object> fields) {
        log.info("IN UserServiceImpl - updateFields: STARTED");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "User not found with id: " + id));
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class, key);
            field.setAccessible(true);
            if (key.equals("password"))
                ReflectionUtils.setField(field, user, passwordEncoder.encode((String) value));
            else if(key.equals("role"))
                ReflectionUtils.setField(field, user, Role.valueOf((String) value));
            else
                ReflectionUtils.setField(field, user, value);
        });
        User userReturned = userRepository.save(user);
        log.info("IN UserServiceImpl - updateFields - FINISHED SUCCESSFULLY");
        return UserMapper.toDTO(user);
    }

    @Override
    public void delete(Long id) {
        log.info("IN UserServiceImpl - delete by id: {} - STARTED", id);
        if (!userRepository.existsById(id))
            throw new ObjectNotFoundException(id.toString(), "User not found with id: " + id);
        userRepository.deleteById(id);
        log.info("IN UserServiceImpl - delete by id: {} - FINISHED SUCCESSFULLY", id);
    }

    @Override
    public void block(Long id) {
        log.info("IN UserServiceImpl - block user by id: {} - STARTED", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "User not found with id: " + id));
        user.setEnabled(false);
        userRepository.save(user);
        log.info("IN UserServiceImpl - block user by id: {} - FINISHED SUCCESSFULLY", id);
    }
}
