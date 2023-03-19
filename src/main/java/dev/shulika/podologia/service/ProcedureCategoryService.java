package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryRequestDTO;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ProcedureCategoryService {
    Page<ProcedureCategoryResponseDTO> findAll(Pageable pageable);

    ProcedureCategoryResponseDTO findById(Long id);

    ProcedureCategoryResponseDTO create(ProcedureCategoryRequestDTO category);

    ProcedureCategoryResponseDTO update(Long id, ProcedureCategoryRequestDTO category);

    ProcedureCategoryResponseDTO updateProcedureFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}
