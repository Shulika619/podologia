package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.auth.AuthRequestDTO;
import dev.shulika.podologia.dto.auth.AuthResponseDTO;
import dev.shulika.podologia.dto.auth.RegisterRequestDTO;
import dev.shulika.podologia.service.impl.AuthServiceImpl;
import dev.shulika.podologia.util.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthServiceImpl authService;
    private final JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registration(
            @RequestBody @Valid RegisterRequestDTO registerRequestDTO
    ) {
        AuthResponseDTO registerResponseDTO = authService.register(registerRequestDTO);
        String token = jwtUtil.generateToken(registerRequestDTO.getEmail());
        ApiResponse<AuthResponseDTO> responseDTO = ApiResponse
                .<AuthResponseDTO>builder()
                .status("SUCCESS")
                .jwtToken(token)
                .data(registerResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        AuthResponseDTO authResponseDTO = authService.login(authRequestDTO);
        String token = jwtUtil.generateToken(authRequestDTO.getEmail());
        ApiResponse<AuthResponseDTO> responseDTO = ApiResponse
                .<AuthResponseDTO>builder()
                .status("SUCCESS")
                .jwtToken(token)
                .data(authResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
