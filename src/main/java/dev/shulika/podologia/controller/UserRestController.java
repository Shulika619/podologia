package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.user.UserRequestDTO;
import dev.shulika.podologia.dto.user.UserResponseDTO;
import dev.shulika.podologia.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Contains all the operations that can be performed on users")
@SecurityRequirement(name = "bearerAuth")
public class UserRestController {
    private final UserServiceImpl userService;

    @GetMapping
    @Operation(summary = "Get all users  *ONLY ADMIN", description = "Get all users *ONLY ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<UserResponseDTO> users = userService.findAll(pageable);
        ApiResponse<List<UserResponseDTO>> responseDTO = ApiResponse
                .<List<UserResponseDTO>>builder()
                .status("SUCCESS")
                .data(users.getContent())
                .totalElements(users.getTotalElements())
                .perPage(users.getSize())
                .currentPage(users.getNumber())
                .totalPages(users.getTotalPages())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Get user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public ResponseEntity<?> findById(@PathVariable long id) {
        UserResponseDTO userResponseDTO = userService.findById(id);
        ApiResponse<UserResponseDTO> responseDTO = ApiResponse
                .<UserResponseDTO>builder()
                .status("SUCCESS")
                .data(userResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create user *ONLY ADMIN", description = "Create user *ONLY ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.create(userRequestDTO);
        ApiResponse<UserResponseDTO> responseDTO = ApiResponse
                .<UserResponseDTO>builder()
                .status("SUCCESS")
                .data(userResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit user: Put", description = "Edit user: Put")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.update(id, userRequestDTO);
        ApiResponse<UserResponseDTO> responseDTO = ApiResponse
                .<UserResponseDTO>builder()
                .status("SUCCESS")
                .data(userResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edit user: Patch *ONLY ADMIN", description = "Edit user: Patch *ONLY ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        UserResponseDTO userResponseDTO = userService.updateFields(id, fields);
        ApiResponse<UserResponseDTO> responseDTO = ApiResponse
                .<UserResponseDTO>builder()
                .status("SUCCESS")
                .data(userResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user *ONLY ADMIN", description = "Delete user *ONLY ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("User deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/block")
    @Operation(summary = "Block user *ONLY ADMIN", description = "Block user *ONLY ADMIN")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> blockById(@PathVariable long id) {
        userService.block(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("User blocked")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
