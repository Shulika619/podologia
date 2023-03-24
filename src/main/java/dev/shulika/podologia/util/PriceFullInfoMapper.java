package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoRequestDTO;
import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoResponseDTO;
import dev.shulika.podologia.model.PriceFullInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceFullInfoMapper {

    public static PriceFullInfoResponseDTO toDTO(PriceFullInfo priceFullInfo) {
        PriceFullInfoResponseDTO priceFullInfoResponseDTO = new PriceFullInfoResponseDTO();
        priceFullInfoResponseDTO.setId(priceFullInfo.getId());
        priceFullInfoResponseDTO.setProcedure(priceFullInfo.getProcedure());
        priceFullInfoResponseDTO.setSpecialist(priceFullInfo.getSpecialist());
        priceFullInfoResponseDTO.setMinutes(priceFullInfo.getMinutes());
        priceFullInfoResponseDTO.setPrice(priceFullInfo.getPrice());
        priceFullInfoResponseDTO.setEnabled(priceFullInfo.getEnabled());
        priceFullInfoResponseDTO.setCreatedAt(priceFullInfo.getCreatedAt());
        priceFullInfoResponseDTO.setUpdatedAt(priceFullInfo.getUpdatedAt());
        return priceFullInfoResponseDTO;
    }

    public static PriceFullInfo fromDTO(PriceFullInfoRequestDTO priceFullInfoRequestDTO) {
        PriceFullInfo priceFullInfo = new PriceFullInfo();
        priceFullInfo.setProcedure(priceFullInfoRequestDTO.getProcedure());
        priceFullInfo.setSpecialist(priceFullInfoRequestDTO.getSpecialist());
        priceFullInfo.setMinutes(priceFullInfoRequestDTO.getMinutes());
        priceFullInfo.setPrice(priceFullInfoRequestDTO.getPrice());
        priceFullInfo.setEnabled(priceFullInfoRequestDTO.getEnabled());
        return priceFullInfo;
    }
}
