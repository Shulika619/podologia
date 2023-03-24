package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryRequestDTO;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryResponseDTO;
import dev.shulika.podologia.model.ProcedureCategory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcedureCategoryMapper {

    public static ProcedureCategoryResponseDTO toDTO(ProcedureCategory procedureCategory) {
        return ProcedureCategoryResponseDTO.builder()
                .id(procedureCategory.getId())
                .category(procedureCategory.getCategory())
                .name(procedureCategory.getName())
                .enabled(procedureCategory.getEnabled())
                .createdAt(procedureCategory.getCreatedAt())
                .updatedAt(procedureCategory.getUpdatedAt())
                .build();
    }

    public static ProcedureCategory fromDTO(ProcedureCategoryRequestDTO procedureCategoryRequestDTO) {
        ProcedureCategory procedureCategory = new ProcedureCategory();
        procedureCategory.setCategory(procedureCategoryRequestDTO.getCategory());
        procedureCategory.setName(procedureCategoryRequestDTO.getName());
        procedureCategory.setEnabled(procedureCategoryRequestDTO.getEnabled());
        return procedureCategory;
    }
}
