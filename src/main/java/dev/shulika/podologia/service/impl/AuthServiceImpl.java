package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.auth.AuthRequestDTO;
import dev.shulika.podologia.dto.auth.AuthResponseDTO;
import dev.shulika.podologia.dto.auth.RegisterRequestDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.User;
import dev.shulika.podologia.repository.UserRepository;
import dev.shulika.podologia.service.AuthService;
import dev.shulika.podologia.util.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        log.info("IN AuthServiceImpl - register: STARTED");
        if (userRepository.existsByEmail(registerRequestDTO.getEmail()))
            throw new ServiceBusinessException(registerRequestDTO.getEmail(), "Email already exists");
        registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        User userReturned = userRepository.save(AuthMapper.fromRegistrationDTO(registerRequestDTO));
        log.info("IN AuthServiceImpl - register - FINISHED SUCCESSFULLY");
        return AuthMapper.toDTO(userReturned);
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        log.info("IN AuthServiceImpl - login: STARTED");
        User user = userRepository.findByEmail(authRequestDTO.getEmail())
                .orElseThrow(() -> new ObjectNotFoundException(authRequestDTO.getEmail(), "Email not found"));
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(),
                        authRequestDTO.getPassword());
        authenticationManager.authenticate(authInputToken);
        log.info("IN AuthServiceImpl - login: FINISHED SUCCESSFULLY");
        return AuthMapper.toDTO(user);
    }
}
