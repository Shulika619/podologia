package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.specialist.SpecialistRequestDTO;
import dev.shulika.podologia.dto.specialist.SpecialistResponseDTO;

import java.util.List;
import java.util.Map;

public interface SpecialistService {
    List<SpecialistResponseDTO> findAll();

    SpecialistResponseDTO findById(Long id);

    void create(SpecialistRequestDTO specialistRequestDTO);

    void update(Long id, SpecialistRequestDTO specialistRequestDTO);

    void updateSpecialistFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}
