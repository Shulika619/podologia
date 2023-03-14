package dev.shulika.podologia.dto.procedure;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcedureResponseDTO {
    private Long id;
    private Long categoryId;
    private String name;
    private Boolean enabled;
}
