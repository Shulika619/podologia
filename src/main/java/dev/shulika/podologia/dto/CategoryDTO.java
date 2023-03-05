package dev.shulika.podologia.dto;

import dev.shulika.podologia.model.Category;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    @NotBlank(message = "Category name shouldn't be NULL OR EMPTY")
    private String categoryName;

    private String description;

    private String status;
}
