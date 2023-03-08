package dev.shulika.podologia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.shulika.podologia.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcedureResponseDTO {
    private Long id;
    private Category category;

    private String procedureName;

    private Integer podologExpertMinutes;

    private Integer podologExpertPrice;

    private Integer podologMinutes;

    private Integer podologPrice;
}
