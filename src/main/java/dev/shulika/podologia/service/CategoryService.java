package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.category.CategoryRequestDTO;
import dev.shulika.podologia.dto.category.CategoryResponseDTO;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<CategoryResponseDTO> findAll();

    CategoryResponseDTO findById(Long id);

    void create(CategoryRequestDTO category);

    void update(Long id, CategoryRequestDTO category);

    void updateCategoryFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}
