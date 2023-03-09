package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.ProcedureRequestDTO;
import dev.shulika.podologia.dto.ProcedureResponseDTO;

import java.util.List;
import java.util.Map;

public interface ProcedureService {
    List<ProcedureResponseDTO> findAll();

    ProcedureResponseDTO findById(Long id);

    void create(ProcedureRequestDTO category);

    void update(Long id, ProcedureRequestDTO category);

    void updateProcedureFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}
