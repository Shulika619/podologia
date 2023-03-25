package dev.shulika.podologia.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 4, max = 100, message = "Password must be between 4 and 100 characters")
    String password;
}
