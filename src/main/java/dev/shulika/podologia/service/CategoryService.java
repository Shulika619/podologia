package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.category.CategoryRequestDTO;
import dev.shulika.podologia.dto.category.CategoryResponseDTO;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Page<CategoryResponseDTO> findAll(Pageable pageable);

    CategoryResponseDTO findById(Long id);

    CategoryResponseDTO create(CategoryRequestDTO category);

    CategoryResponseDTO update(Long id, CategoryRequestDTO category);

    CategoryResponseDTO updateCategoryFields(Long id, Map<String, Object> fields);

    void delete(Long id);

    List<ProcedureCategoryResponseDTO> findByIdAndAllProcedures(Long id);
}
