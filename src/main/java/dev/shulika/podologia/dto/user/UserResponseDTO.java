package dev.shulika.podologia.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private Boolean enabled;
    private Date createdAt;
    private Date updatedAt;
}
