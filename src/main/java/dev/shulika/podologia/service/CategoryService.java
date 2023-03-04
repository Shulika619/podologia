package dev.shulika.podologia.service;

import dev.shulika.podologia.model.Category;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Boolean existsById(Long id);

    void create(Category category);

    void update(Long id, Category category);
    void updateCategoryFields(Long id, Map<String, Object> fields);
    void delete(Long id);

    Optional<Category> findByCategoryName(String name);
    Boolean existsByCategoryName(String name);
}
