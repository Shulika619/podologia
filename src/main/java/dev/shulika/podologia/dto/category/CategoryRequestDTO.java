package dev.shulika.podologia.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    @Schema(description = "name", example = "Category name")
    @NotBlank(message = "Category name shouldn't be NULL OR EMPTY")
    @Size(min = 3, max = 65, message = "Category name must be between 3 and 65 characters")
    private String name;

    @Schema(description = "description", example = "Category description")
    private String description;

    @Schema(description = "enabled", example = "true")
    @NotNull(message = "Category field 'enabled' must be bool and not null")
    private Boolean enabled;
}
