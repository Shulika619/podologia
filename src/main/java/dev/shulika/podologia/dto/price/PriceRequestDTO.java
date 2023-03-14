package dev.shulika.podologia.dto.price;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class PriceRequestDTO {
    @NotNull(message = "ProcedureId shouldn't be NULL")
    @Min(value = 1, message = "ProcedureId min value 1")
    private Long procedureId;
    @NotNull(message = "SpecialistId shouldn't be NULL")
    @Min(value = 1, message = "SpecialistId min value 1")
    private Long specialistId;
    @Range(min = 0, max = 360, message = "Procedure* minutes must be between 0 and 360")
    private Integer minutes;
    @Range(min = 0, max = 50000, message = "Procedure price bust be between 0 and 50000")
    private Integer price;
}
