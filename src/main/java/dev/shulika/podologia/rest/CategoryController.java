package dev.shulika.podologia.rest;

import dev.shulika.podologia.model.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.shulika.podologia.service.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

}