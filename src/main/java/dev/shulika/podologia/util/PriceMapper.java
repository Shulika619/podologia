package dev.shulika.podologia.util;
import dev.shulika.podologia.dto.price.PriceRequestDTO;
import dev.shulika.podologia.dto.price.PriceResponseDTO;
import dev.shulika.podologia.model.Price;
import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.model.Specialist;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class PriceMapper {

    public static PriceResponseDTO toDTO(Price price){
        PriceResponseDTO priceResponseDTO = new PriceResponseDTO();
        priceResponseDTO.setId(price.getId());
        priceResponseDTO.setProcedure(price.getProcedure());
        priceResponseDTO.setSpecialist(price.getSpecialist());
        priceResponseDTO.setMinutes(price.getMinutes());
        priceResponseDTO.setPrice(price.getPrice());
        priceResponseDTO.setCreatedAt(price.getCreatedAt());
        priceResponseDTO.setUpdatedAt(price.getUpdatedAt());
        return priceResponseDTO;
    }

    public static Price fromDTO(PriceRequestDTO priceRequestDTO){
        Price price = new Price();

        price.setProcedure(priceRequestDTO.getProcedure());
        price.setSpecialist(priceRequestDTO.getSpecialist());
        price.setMinutes(priceRequestDTO.getMinutes());
        price.setPrice(priceRequestDTO.getPrice());

        return price;
    }
}
