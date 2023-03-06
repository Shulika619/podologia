package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.CategoryRequestDTO;
import dev.shulika.podologia.dto.CategoryResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.Category;
import dev.shulika.podologia.service.CategoryService;
import dev.shulika.podologia.util.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.shulika.podologia.repository.CategoryRepository;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> findAll() {
        log.info("IN CategoryServiceImpl - findAll: STARTED");
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty())
            return Collections.emptyList();
        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        log.info("IN CategoryServiceImpl - findById: STARTED");
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "Category not found with id: " + id));
        return CategoryMapper.toDTO(category);
    }

    @Override
    public Boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public void create(CategoryRequestDTO categoryRequestDTO) {
        log.info("IN CategoryServiceImpl - create: STARTED");
        if (categoryRepository.existsByCategoryName(categoryRequestDTO.getCategoryName()))
            throw new ServiceBusinessException(categoryRequestDTO.getCategoryName(), "A category with this name already exists");
        categoryRepository.save(CategoryMapper.fromDTO(categoryRequestDTO));
    }

    @Override
    public void update(Long id, CategoryRequestDTO categoryRequestDTO) {
        log.info("IN CategoryServiceImpl - update: STARTED");
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (!existingCategory.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Category not found with id: " + id);
        Category category = existingCategory.get();
        category.setCategoryName(categoryRequestDTO.getCategoryName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setStatus(categoryRequestDTO.getStatus());
        categoryRepository.save(category);
    }

    @Override
    public void updateCategoryFields(Long id, Map<String, Object> fields) {     // TODO: add validate
        log.info("IN CategoryServiceImpl - updateCategoryFields: STARTED");
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Category.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingCategory.get(), value);
            });
            categoryRepository.save(existingCategory.get());
        }
        throw new ObjectNotFoundException(id.toString(), "Category not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        log.info("IN CategoryServiceImpl - delete: STARTED");
        if (!categoryRepository.existsById(id))
            throw new ObjectNotFoundException(id.toString(), "Category not found with id: " + id);
        categoryRepository.deleteById(id);
    }


    // TODO: delete if dont need
    @Override
    public Optional<Category> findByCategoryName(String name) {
        return Optional.ofNullable(categoryRepository.findByCategoryName(name).orElse(null));
    }

    @Override
    public Boolean existsByCategoryName(String name) {
        return categoryRepository.existsByCategoryName(name);
    }
}