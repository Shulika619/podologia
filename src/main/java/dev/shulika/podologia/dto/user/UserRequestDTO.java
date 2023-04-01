package dev.shulika.podologia.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    @NotBlank(message = "First name shouldn't be NULL OR EMPTY")
    @Size(min = 3, max = 65, message = "First name must be between 3 and 65 characters")
    private String firstname;
    @NotBlank(message = "Last name shouldn't be NULL OR EMPTY")
    @Size(min = 3, max = 65, message = "Last name must be between 3 and 65 characters")
    private String lastname;
    @Email(message = "Invalid email format")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password must not be empty or null")
    @Size(min = 4, max = 100, message = "Password must be between 4 and 100 characters")
    private String password;
}
