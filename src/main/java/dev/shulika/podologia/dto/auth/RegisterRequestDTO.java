package dev.shulika.podologia.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description="RegisterRequestDTO")
public class RegisterRequestDTO {
    @Schema(description="firstname", example = "Ivan")
    @NotBlank(message = "Firs name must not be empty or null")
    @Size(min = 3, max = 100, message = "Firs name must be between 3 and 100 characters")
    private String firstname;

    @Schema(description="lastname", example = "Ivanov")
    @NotBlank(message = "Last name must not be empty or null")
    @Size(min = 3, max = 100, message = "Last name must be between 3 and 100 characters")
    private String lastname;

    @Schema(description="email", example = "test@gmail.com")
    @NotBlank(message = "Email must not be empty or null")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description="password", example = "1234")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password must not be empty or null")
    @Size(min = 4, max = 100, message = "Password must be between 4 and 100 characters")
    private String password;
}
