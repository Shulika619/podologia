package dev.shulika.podologia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse<T> {
    private String status;
    private Long totalElements;
    private Integer perPage;
    private Integer currentPage;
    private Integer totalPages;
    private List<ErrorDTO> errors;
    private T data;

}

