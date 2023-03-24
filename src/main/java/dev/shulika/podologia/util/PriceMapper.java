package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.price.PriceRequestDTO;
import dev.shulika.podologia.dto.price.PriceResponseDTO;
import dev.shulika.podologia.model.Price;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceMapper {

    public static PriceResponseDTO toDTO(Price price) {
        PriceResponseDTO priceResponseDTO = new PriceResponseDTO();
        priceResponseDTO.setId(price.getId());
        priceResponseDTO.setProcedureId(price.getProcedureId());
        priceResponseDTO.setSpecialistId(price.getSpecialistId());
        priceResponseDTO.setMinutes(price.getMinutes());
        priceResponseDTO.setPrice(price.getPrice());
        priceResponseDTO.setEnabled(price.getEnabled());
        priceResponseDTO.setCreatedAt(price.getCreatedAt());
        priceResponseDTO.setUpdatedAt(price.getUpdatedAt());
        return priceResponseDTO;
    }

    public static Price fromDTO(PriceRequestDTO priceRequestDTO) {
        Price price = new Price();
        price.setProcedureId(priceRequestDTO.getProcedureId());
        price.setSpecialistId(priceRequestDTO.getSpecialistId());
        price.setMinutes(priceRequestDTO.getMinutes());
        price.setPrice(priceRequestDTO.getPrice());
        price.setEnabled(priceRequestDTO.getEnabled());
        return price;
    }
}
