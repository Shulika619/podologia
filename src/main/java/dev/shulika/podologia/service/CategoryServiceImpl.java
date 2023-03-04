package dev.shulika.podologia.service;

import dev.shulika.podologia.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.shulika.podologia.repository.CategoryRepository;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public Boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public void create(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Long id, Category category) {
        categoryRepository.findById(id);    // TODO:
    }

    @Override
    public void updateCategoryFields(Long id, Map<String, Object> fields) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Category.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingCategory.get(), value);
            });
           categoryRepository.save(existingCategory.get());
        }
//        return null;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }


    // TODO: if need
    @Override
    public Optional<Category> findByCategoryName(String name) {
        return Optional.ofNullable(categoryRepository.findByCategoryName(name).orElse(null));
    }

    @Override
    public Boolean existsByCategoryName(String name) {
        return categoryRepository.existsByCategoryName(name);
    }
}