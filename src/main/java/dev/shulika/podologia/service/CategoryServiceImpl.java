package dev.shulika.podologia.service;

import dev.shulika.podologia.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.shulika.podologia.repository.CategoryRepository;

import java.util.List;
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
    public Optional<Category> findByCategoryName(String name) {
        return Optional.empty();
    }

    @Override
    public Boolean existsByCategoryName(String name) {
        return null;
    }
}