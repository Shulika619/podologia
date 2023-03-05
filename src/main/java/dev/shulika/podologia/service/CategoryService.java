package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.CategoryRequestDTO;
import dev.shulika.podologia.dto.CategoryResponseDTO;
import dev.shulika.podologia.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResponseDTO> findAll();
    CategoryResponseDTO findById(Long id);
    Boolean existsById(Long id);

    void create(CategoryRequestDTO category);

    void update(Long id, CategoryRequestDTO category);
    void updateCategoryFields(Long id, Map<String, Object> fields);
    void delete(Long id);

    Optional<Category> findByCategoryName(String name);
    Boolean existsByCategoryName(String name);
}
