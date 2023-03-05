package dev.shulika.podologia.rest;

import dev.shulika.podologia.dto.CategoryDTO;
import dev.shulika.podologia.model.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import dev.shulika.podologia.service.CategoryServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryRestController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CategoryDTO category) {
        categoryService.create(category);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid CategoryDTO category) {
        categoryService.update(id, category);
    }

    @PatchMapping("/{id}")
    public void updateProductFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        categoryService.updateCategoryFields(id, fields);
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }
//    @GetMapping("/{name}")
//    public Optional<Category> findByName(@PathVariable String name){
//        return categoryService.findByCategoryName(name);
//    }

}