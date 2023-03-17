package dev.shulika.podologia.util;

import dev.shulika.podologia.dto.procedure.ProcedureRequestDTO;
import dev.shulika.podologia.dto.procedure.ProcedureResponseDTO;
import dev.shulika.podologia.model.Procedure;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcedureMapper {

    public static ProcedureResponseDTO toDTO(Procedure procedure) {
        ProcedureResponseDTO procedureResponseDTO = new ProcedureResponseDTO();
        procedureResponseDTO.setId(procedure.getId());
        procedureResponseDTO.setCategoryId(procedure.getCategoryId());
        procedureResponseDTO.setName(procedure.getName());
        procedureResponseDTO.setEnabled(procedure.getEnabled());
        return procedureResponseDTO;
    }

    public static Procedure fromDTO(ProcedureRequestDTO procedureRequestDTO) {
        Procedure procedure = new Procedure();
        procedure.setCategoryId(procedureRequestDTO.getCategoryId());
        procedure.setName(procedureRequestDTO.getName());
        procedure.setEnabled(procedureRequestDTO.getEnabled());
        return procedure;
    }
}
