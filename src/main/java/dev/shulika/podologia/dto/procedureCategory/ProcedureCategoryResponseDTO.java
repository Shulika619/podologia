package dev.shulika.podologia.dto.procedureCategory;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.shulika.podologia.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProcedureCategoryResponseDTO {
    private Long id;
    private String name;
    private Boolean enabled;
    private Date createdAt;
    private Date updatedAt;
    private Category category;
}
