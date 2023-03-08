package dev.shulika.podologia.dto;

import dev.shulika.podologia.model.Category;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class ProcedureRequestDTO {

    private Category category;

    @NotBlank(message = "Procedure name shouldn't be NULL OR EMPTY")
    @Size(min = 3, max = 255, message = "Category name must be between 3 and 255 characters")
    private String procedureName;

    @Range(min = 0, max = 240, message = "Procedure Podolog-Expert minutes must be between 0 and 240")
    private Integer podologExpertMinutes;

    @Range(min = 0, max = 50000, message = "Procedure Podolog-Expert price bust be between 0 and 50000")
    private Integer podologExpertPrice;

    @Range(min = 0, max = 240, message = "Procedure Podolog minutes must be between 0 and 240")
    private Integer podologMinutes;

    @Range(min = 0, max = 50000, message = "Procedure Podolog price bust be between 0 and 50000")
    private Integer podologPrice;
}
