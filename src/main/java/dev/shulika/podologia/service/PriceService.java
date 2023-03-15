package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.price.PriceRequestDTO;
import dev.shulika.podologia.dto.price.PriceResponseDTO;

import java.util.List;
import java.util.Map;

public interface PriceService {
    List<PriceResponseDTO> findAll();

    PriceResponseDTO findById(Long id);

    void create(PriceRequestDTO category);

    void update(Long id, PriceRequestDTO category);

    void delete(Long id);
}