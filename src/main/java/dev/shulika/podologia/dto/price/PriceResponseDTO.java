package dev.shulika.podologia.dto.price;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.model.Specialist;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceResponseDTO {
    private Long id;
    private Procedure procedure;
    private Specialist specialist;
    private Integer minutes;
    private Integer price;
    private Date createdAt;
    private Date updatedAt;
}
