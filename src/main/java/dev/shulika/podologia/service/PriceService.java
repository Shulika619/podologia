package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.price.PriceRequestDTO;
import dev.shulika.podologia.dto.price.PriceResponseDTO;
import dev.shulika.podologia.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PriceService {
    Page<PriceResponseDTO> findAllByPage(Pageable pageable);

    PriceResponseDTO findById(Long id);

    PriceResponseDTO create(PriceRequestDTO category);

    PriceResponseDTO update(Long id, PriceRequestDTO category);

    PriceResponseDTO updateFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}