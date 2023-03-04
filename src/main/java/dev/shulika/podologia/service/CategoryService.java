package dev.shulika.podologia.service;

import dev.shulika.podologia.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findByCategoryName(String name);
    Boolean existsByCategoryName(String name);
}
