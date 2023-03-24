package dev.shulika.podologia.dto.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceResponseDTO {
    private Long id;
    private Long procedureId;
    private Long specialistId;
    private Integer minutes;
    private Integer price;
    private Boolean enabled;
    private Date createdAt;
    private Date updatedAt;
}
