package dev.shulika.podologia.dto.price;

import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.model.Specialist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class PriceRequestDTO {
    @NotNull(message = "Procedure shouldn't be NULL, required field -> procedure: {id:int}")
    private Procedure procedure;
    @NotNull(message = "Specialist shouldn't be NULL, required field -> specialist: {id:int}")
    private Specialist specialist;
    @Range(min = 0, max = 360, message = "Procedure minutes must be between 0 and 360")
    private Integer minutes;
    @Range(min = 0, max = 50000, message = "Procedure price bust be between 0 and 50000")
    private Integer price;
    @NotNull(message = "Procedure enabled shouldn't be NULL")
    private Boolean enabled;
}
