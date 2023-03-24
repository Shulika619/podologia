package dev.shulika.podologia.dto.price;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class PriceRequestDTO {
    @Min(value = 1, message = "Procedure id must be 1+")
    @NotNull(message = "Procedure id shouldn't be NULL, required field -> procedure: {id:int}")
    private Long procedureId;
    @Min(value = 1, message = "Specialist id must be 1+")
    @NotNull(message = "Specialist id shouldn't be NULL, required field -> specialist: {id:int}")
    private Long specialistId;
    @Range(min = 0, max = 360, message = "Procedure minutes must be between 0 and 360")
    private Integer minutes;
    @Range(min = 0, max = 50000, message = "Procedure price bust be between 0 and 50000")
    private Integer price;
    @NotNull(message = "Procedure enabled shouldn't be NULL")
    private Boolean enabled;
}
