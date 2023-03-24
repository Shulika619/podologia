package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.specialist.SpecialistRequestDTO;
import dev.shulika.podologia.dto.specialist.SpecialistResponseDTO;
import dev.shulika.podologia.model.Specialist;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SpecialistMapper {

    public static SpecialistResponseDTO toDTO(Specialist specialist) {
        SpecialistResponseDTO specialistResponseDTO = new SpecialistResponseDTO();
        specialistResponseDTO.setId(specialist.getId());
        specialistResponseDTO.setName(specialist.getName());
        specialistResponseDTO.setDescription(specialist.getDescription());
        specialistResponseDTO.setEnabled(specialist.getEnabled());
        specialistResponseDTO.setCreatedAt(specialist.getCreatedAt());
        specialistResponseDTO.setUpdatedAt(specialist.getUpdatedAt());
        return specialistResponseDTO;
    }

    public static Specialist fromDTO(SpecialistRequestDTO specialistRequestDTO) {
        Specialist specialist = new Specialist();
        specialist.setName(specialistRequestDTO.getName());
        specialist.setDescription(specialistRequestDTO.getDescription());
        specialist.setEnabled(specialistRequestDTO.getEnabled());
        return specialist;
    }
}
