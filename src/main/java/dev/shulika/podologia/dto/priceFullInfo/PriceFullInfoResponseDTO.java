package dev.shulika.podologia.dto.priceFullInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.model.Specialist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceFullInfoResponseDTO {
    private Long id;
    private Procedure procedure;
    private Specialist specialist;
    private Integer minutes;
    private Integer price;
    private Boolean enabled;
    private Date createdAt;
    private Date updatedAt;
}
