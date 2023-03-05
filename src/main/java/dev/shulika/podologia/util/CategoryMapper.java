package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.CategoryDTO;
import dev.shulika.podologia.model.Category;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setStatus(category.getStatus());
        return categoryDTO;
    }

    public static Category fromDTO(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(categoryDTO.getStatus());
        return category;
    }
}
