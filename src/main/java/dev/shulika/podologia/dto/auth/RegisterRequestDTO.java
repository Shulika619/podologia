package dev.shulika.podologia.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "Firs name must not be empty or null")
    @Size(min = 3, max = 100, message = "Firs name must be between 3 and 100 characters")
    private String firstname;

    @NotBlank(message = "Last name must not be empty or null")
    @Size(min = 3, max = 100, message = "Last name must be between 3 and 100 characters")
    private String lastname;
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password must not be empty or null")
    @Size(min = 4, max = 100, message = "Password must be between 4 and 100 characters")
    private String password;
}
