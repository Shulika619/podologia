package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.model.User;
import dev.shulika.podologia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email){
        log.info("IN UserDetailsServiceImpl - loadUserByUsername: STARTED");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(email, "Email not found "));
        log.info("IN UserDetailsServiceImpl - loadUserByUsername() - findByEmail: {} - FINISHED SUCCESSFULLY", email);
        return user;
    }
}
