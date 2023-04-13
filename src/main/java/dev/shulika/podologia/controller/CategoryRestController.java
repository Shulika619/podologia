package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.category.CategoryRequestDTO;
import dev.shulika.podologia.dto.category.CategoryResponseDTO;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryResponseDTO;
import dev.shulika.podologia.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Category", description = "Categories for procedures, contains all the operations that can be performed on category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Get all categories")
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
    @Operation(summary = "Get category by id", description = "Get category by id")
    public ResponseEntity<?> findById(@PathVariable long id) {
        CategoryResponseDTO categoryResponseDTO = categoryService.findById(id);
        ApiResponse<CategoryResponseDTO> responseDTO = ApiResponse
                .<CategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(categoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}/procedures")
    @Operation(summary = "Get category by id and all procedures in this category", description = "Get category by id and all procedures in this category")
    public ResponseEntity<?> findByIdAndAllProcedures(@PathVariable long id) {
        List<ProcedureCategoryResponseDTO> proceduresDTO = categoryService.findByIdAndAllProcedures(id);
        ApiResponse<List<ProcedureCategoryResponseDTO>> responseDTO = ApiResponse
                .<List<ProcedureCategoryResponseDTO>>builder()
                .status("SUCCESS")
                .data(proceduresDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create category", description = "Create category")
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
    @Operation(summary = "Edit category: Put", description = "Edit category: Put")
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
    @Operation(summary = "Edit category: Patch", description = "Edit category: Patch")
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
    @Operation(summary = "Delete category", description = "Delete category")
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