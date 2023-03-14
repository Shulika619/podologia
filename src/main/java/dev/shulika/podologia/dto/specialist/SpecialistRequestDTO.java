package dev.shulika.podologia.dto.specialist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialistRequestDTO {
    @NotBlank(message = "Procedure name shouldn't be NULL OR EMPTY")
    @Size(min = 3, max = 255, message = "Category name must be between 3 and 255 characters")
    private String name;

    private String description;

    @NotNull(message = "Procedure enabled shouldn't be NULL")
    private Boolean enabled;
}
