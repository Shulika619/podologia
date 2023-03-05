package dev.shulika.podologia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    @NotBlank(message = "Category name shouldn't be NULL OR EMPTY")
    @Size(min = 3, max = 65,message = "Category name must be between 3 and 65 characters")
    private String categoryName;

    private String description;

    private String status;
}
