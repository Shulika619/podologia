package dev.shulika.podologia.dto.procedure;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureRequestDTO {

    @Min(value = 1, message = "Procedure categoryId must be 1+")
    @NotNull(message = "Procedure categoryId shouldn't be NULL")
    private Long categoryId;

    @NotBlank(message = "Procedure name shouldn't be NULL OR EMPTY")
    @Size(min = 3, max = 255, message = "Category name must be between 3 and 255 characters")
    private String name;

    @NotNull(message = "Procedure enabled shouldn't be NULL")
    private Boolean enabled;

}
