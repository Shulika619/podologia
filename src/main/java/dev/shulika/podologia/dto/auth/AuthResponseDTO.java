package dev.shulika.podologia.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private Boolean enabled;
    private Date createdAt;
}
