package dev.shulika.podologia.util;
import dev.shulika.podologia.dto.ProcedureRequestDTO;
import dev.shulika.podologia.dto.ProcedureResponseDTO;
import dev.shulika.podologia.model.Procedure;

public class ProcedureMapper {
    public static ProcedureResponseDTO toDTO(Procedure procedure){
        ProcedureResponseDTO procedureResponseDTO = new ProcedureResponseDTO();
        procedureResponseDTO.setId(procedure.getId());
        procedureResponseDTO.setCategoryId(procedure.getCategoryId());
        procedureResponseDTO.setName(procedure.getName());
        procedureResponseDTO.setPodologExpertMinutes(procedure.getPodologExpertMinutes());
        procedureResponseDTO.setPodologExpertPrice(procedure.getPodologExpertPrice());;
        procedureResponseDTO.setPodologMinutes(procedure.getPodologMinutes());
        procedureResponseDTO.setPodologPrice(procedure.getPodologPrice());
        procedureResponseDTO.setEnabled(procedure.getEnabled());
        return procedureResponseDTO;
    }

    public static Procedure fromDTO(ProcedureRequestDTO procedureRequestDTO){
        Procedure procedure = new Procedure();
        procedure.setCategoryId(procedureRequestDTO.getCategoryId());
        procedure.setName(procedureRequestDTO.getName());
        procedure.setPodologExpertMinutes(procedureRequestDTO.getPodologExpertMinutes());
        procedure.setPodologExpertPrice(procedureRequestDTO.getPodologExpertPrice());;
        procedure.setPodologMinutes(procedureRequestDTO.getPodologMinutes());
        procedure.setPodologPrice(procedureRequestDTO.getPodologPrice());
        procedure.setEnabled(procedureRequestDTO.getEnabled());
        return procedure;
    }
}
