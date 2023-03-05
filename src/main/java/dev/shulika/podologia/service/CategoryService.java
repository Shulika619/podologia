package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.CategoryDTO;
import dev.shulika.podologia.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    Boolean existsById(Long id);

    void create(CategoryDTO category);

    void update(Long id, CategoryDTO category);
    void updateCategoryFields(Long id, Map<String, Object> fields);
    void delete(Long id);

    Optional<Category> findByCategoryName(String name);
    Boolean existsByCategoryName(String name);
}
