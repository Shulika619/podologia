package dev.shulika.podologia.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean enabled;
    private Date createdAt;
    private Date updatedAt;
}
