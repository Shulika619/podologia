package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoRequestDTO;
import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PriceFullInfoService {
    Page<PriceFullInfoResponseDTO> findAllByPage(Pageable pageable);

    PriceFullInfoResponseDTO findById(Long id);

    PriceFullInfoResponseDTO create(PriceFullInfoRequestDTO priceFullInfoRequestDTO);

    PriceFullInfoResponseDTO update(Long id, PriceFullInfoRequestDTO priceFullInfoRequestDTO);
    PriceFullInfoResponseDTO updateFields(Long id, Map<String, Object> fields);

    void delete(Long id);
}