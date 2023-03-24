package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.specialist.SpecialistRequestDTO;
import dev.shulika.podologia.dto.specialist.SpecialistResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface SpecialistService {
    Page<SpecialistResponseDTO> findAll(Pageable pageable);

    SpecialistResponseDTO findById(Long id);

    SpecialistResponseDTO create(SpecialistRequestDTO specialistRequestDTO);

    SpecialistResponseDTO update(Long id, SpecialistRequestDTO specialistRequestDTO);

    SpecialistResponseDTO updateSpecialistFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}
