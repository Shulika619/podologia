package dev.shulika.podologia.rest;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.CategoryRequestDTO;
import dev.shulika.podologia.dto.CategoryResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.shulika.podologia.service.impl.CategoryServiceImpl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryRestController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        log.info("IN CategoryRestController - findAll");
        List<CategoryResponseDTO> categories = categoryService.findAll();
        ApiResponse<List<CategoryResponseDTO>> responseDTO = ApiResponse
                .<List<CategoryResponseDTO>>builder()
                .status("SUCCESS")
                .results(categories)
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        log.info("IN CategoryRestController - findById: {}", id);
        CategoryResponseDTO categoryResponseDTO = categoryService.findById(id);
        ApiResponse<CategoryResponseDTO> responseDTO = ApiResponse
                .<CategoryResponseDTO>builder()
                .status("SUCCESS")
                .results(categoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CategoryRequestDTO category) {
        categoryService.create(category);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO category) {
        categoryService.update(id, category);
    }

    @PatchMapping("/{id}")
    public void updateProductFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        categoryService.updateCategoryFields(id, fields);
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
//    @GetMapping("/{name}")
//    public Optional<Category> findByName(@PathVariable String name){
//        return categoryService.findByCategoryName(name);
//    }

}