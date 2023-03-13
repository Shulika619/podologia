package dev.shulika.podologia.service.impl;

import dev.shulika.podologia.dto.CategoryRequestDTO;
import dev.shulika.podologia.dto.CategoryResponseDTO;
import dev.shulika.podologia.exception.ObjectNotFoundException;
import dev.shulika.podologia.exception.ServiceBusinessException;
import dev.shulika.podologia.model.Category;
import dev.shulika.podologia.service.CategoryService;
import dev.shulika.podologia.util.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.shulika.podologia.repository.CategoryRepository;
import org.springframework.util.ReflectionUtils;

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

    @Cacheable("categories")
    @Override
    public List<CategoryResponseDTO> findAll() {
        log.info("IN CategoryServiceImpl - findAll - STARTED");
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty())
            return Collections.emptyList();
        log.info("IN CategoryServiceImpl - findAll - FINISHED SUCCESSFULLY - CategoryMapper::toDTO NOW");
        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        log.info("IN CategoryServiceImpl - findById: {} - STARTED", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id.toString(), "Category not found with id: " + id));
        log.info("IN CategoryServiceImpl - findById: {} - FINISHED SUCCESSFULLY - CategoryMapper.toDTO NOW", id);
        return CategoryMapper.toDTO(category);
    }

    @Override
    public void create(CategoryRequestDTO categoryRequestDTO) {
        log.info("IN CategoryServiceImpl - create: STARTED");
        if (categoryRepository.existsByName(categoryRequestDTO.getName()))
            throw new ServiceBusinessException(categoryRequestDTO.getName(), "A category with this name already exists");
        categoryRepository.save(CategoryMapper.fromDTO(categoryRequestDTO));
        log.info("IN CategoryServiceImpl - created - FINISHED SUCCESSFULLY");
    }

    @Override
    public void update(Long id, CategoryRequestDTO categoryRequestDTO) {
        log.info("IN CategoryServiceImpl - update category by id: {} - STARTED", id);
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (!existingCategory.isPresent())
            throw new ObjectNotFoundException(id.toString(), "Category not found with id: " + id);
        Category category = existingCategory.get();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setEnabled(categoryRequestDTO.getEnabled());
        categoryRepository.save(category);
        log.info("IN CategoryServiceImpl - update category by id: {} - FINISHED SUCCESSFULLY", id);
    }

    @Override
    public void updateCategoryFields(Long id, Map<String, Object> fields) {     // TODO: add validate
        log.info("IN CategoryServiceImpl - updateCategoryFields: STARTED");
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (!existingCategory.isPresent()) {
            throw new ObjectNotFoundException(id.toString(), "Category not found with id: " + id);
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Category.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingCategory.get(), value);
        });
        categoryRepository.save(existingCategory.get());
        log.info("IN CategoryServiceImpl - updateCategoryFields - FINISHED SUCCESSFULLY");
    }

    @Override
    public void delete(Long id) {
        log.info("IN CategoryServiceImpl - delete by id: {} - STARTED", id);
        if (!categoryRepository.existsById(id))
            throw new ObjectNotFoundException(id.toString(), "Category not found with id: " + id);
        categoryRepository.deleteById(id);
        log.info("IN CategoryServiceImpl - delete by id: {} - FINISHED SUCCESSFULLY", id);
    }
}