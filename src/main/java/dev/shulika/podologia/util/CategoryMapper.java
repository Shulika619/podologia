package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.category.CategoryRequestDTO;
import dev.shulika.podologia.dto.category.CategoryResponseDTO;
import dev.shulika.podologia.model.Category;

public class CategoryMapper {

    public static CategoryResponseDTO toDTO(Category category) {
        CategoryResponseDTO categoryRequestDTO = new CategoryResponseDTO();
        categoryRequestDTO.setId(category.getId());
        categoryRequestDTO.setName(category.getName());
        categoryRequestDTO.setDescription(category.getDescription());
        categoryRequestDTO.setEnabled(category.getEnabled());
        categoryRequestDTO.setCreatedAt(category.getCreatedAt());
        categoryRequestDTO.setUpdatedAt(category.getUpdatedAt());
        return categoryRequestDTO;
    }

    public static Category fromDTO(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setEnabled(categoryRequestDTO.getEnabled());
        return category;
    }
}
