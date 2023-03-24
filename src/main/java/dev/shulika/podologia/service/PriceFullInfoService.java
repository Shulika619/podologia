package dev.shulika.podologia.service;

import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoRequestDTO;
import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PriceFullInfoService {
    Page<PriceFullInfoResponseDTO> findAllByPage(Pageable pageable);

    PriceFullInfoResponseDTO findById(Long id);

    void create(PriceFullInfoRequestDTO priceFullInfoRequestDTO);

    void update(Long id, PriceFullInfoRequestDTO priceFullInfoRequestDTO);

    void delete(Long id);
}