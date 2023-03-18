package dev.shulika.podologia.rest;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.category.CategoryRequestDTO;
import dev.shulika.podologia.dto.category.CategoryResponseDTO;
import dev.shulika.podologia.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<CategoryResponseDTO> categories = categoryService.findAll(pageable);
        ApiResponse<List<CategoryResponseDTO>> responseDTO = ApiResponse
                .<List<CategoryResponseDTO>>builder()
                .status("SUCCESS")
                .data(categories.getContent())
                .totalElements(categories.getTotalElements())
                .perPage(categories.getSize())
                .currentPage(categories.getNumber())
                .totalPages(categories.getTotalPages())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        CategoryResponseDTO categoryResponseDTO = categoryService.findById(id);
        ApiResponse<CategoryResponseDTO> responseDTO = ApiResponse
                .<CategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(categoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO categoryResponseDTO = categoryService.create(categoryRequestDTO);
        ApiResponse<CategoryResponseDTO> responseDTO = ApiResponse
                .<CategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(categoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO categoryResponseDTO = categoryService.update(id, categoryRequestDTO);
        ApiResponse<CategoryResponseDTO> responseDTO = ApiResponse
                .<CategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(categoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProductFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        CategoryResponseDTO categoryResponseDTO = categoryService.updateCategoryFields(id, fields);
        ApiResponse<CategoryResponseDTO> responseDTO = ApiResponse
                .<CategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(categoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("Category deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}