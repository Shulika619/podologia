package dev.shulika.podologia.dto.procedureCategory;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.shulika.podologia.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProcedureCategoryRequestDTO {

    @NotBlank(message = "Procedure name shouldn't be NULL OR EMPTY")
    @Size(min = 3, max = 255, message = "Procedure name must be between 3 and 255 characters")
    private String name;

    @NotNull(message = "Procedure enabled shouldn't be NULL")
    private Boolean enabled;

    @NotNull(message = "Procedure shouldn't be NULL")
    private Category category;
}
