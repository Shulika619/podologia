package dev.shulika.podologia.dto.specialist;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecialistResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean enabled;
    private Date createdAt;
    private Date updatedAt;
}
