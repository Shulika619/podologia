package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.CategoryRequestDTO;
import dev.shulika.podologia.dto.CategoryResponseDTO;
import dev.shulika.podologia.model.Category;

public class CategoryMapper {
    public static CategoryResponseDTO toDTO(Category category){
        CategoryResponseDTO categoryRequestDTO = new CategoryResponseDTO();
        categoryRequestDTO.setId(category.getId());
        categoryRequestDTO.setCategoryName(category.getCategoryName());
        categoryRequestDTO.setDescription(category.getDescription());
        categoryRequestDTO.setIsActive(category.getIsActive());
        return categoryRequestDTO;
    }

    public static Category fromDTO(CategoryRequestDTO categoryRequestDTO){
        Category category = new Category();
        category.setCategoryName(categoryRequestDTO.getCategoryName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setIsActive(categoryRequestDTO.getIsActive());
        return category;
    }
}
