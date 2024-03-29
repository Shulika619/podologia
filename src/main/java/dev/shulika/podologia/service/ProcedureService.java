package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.procedure.ProcedureRequestDTO;
import dev.shulika.podologia.dto.procedure.ProcedureResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ProcedureService {
    Page<ProcedureResponseDTO> findAll(Pageable pageable);

    ProcedureResponseDTO findById(Long id);

    ProcedureResponseDTO create(ProcedureRequestDTO category);

    ProcedureResponseDTO update(Long id, ProcedureRequestDTO category);

    ProcedureResponseDTO updateProcedureFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}
