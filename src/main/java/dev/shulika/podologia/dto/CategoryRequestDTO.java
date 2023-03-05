package dev.shulika.podologia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    @NotBlank(message = "Category name shouldn't be NULL OR EMPTY")
    private String categoryName;

    private String description;

    private String status;
}
