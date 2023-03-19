package dev.shulika.podologia.rest;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryRequestDTO;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryResponseDTO;
import dev.shulika.podologia.service.ProcedureCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/procedures-with-categories")
@RequiredArgsConstructor
public class ProcedureCategoryRestController {
    private final ProcedureCategoryService procedureCategoryService;

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<ProcedureCategoryResponseDTO> procedures = procedureCategoryService.findAll(pageable);
        ApiResponse<List<ProcedureCategoryResponseDTO>> responseDTO = ApiResponse
                .<List<ProcedureCategoryResponseDTO>>builder()
                .status("SUCCESS")
                .data(procedures.getContent())
                .totalElements(procedures.getTotalElements())
                .perPage(procedures.getSize())
                .currentPage(procedures.getNumber())
                .totalPages(procedures.getTotalPages())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        ProcedureCategoryResponseDTO procedureResponseDTO = procedureCategoryService.findById(id);
        ApiResponse<ProcedureCategoryResponseDTO> responseDTO = ApiResponse
                .<ProcedureCategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(procedureResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProcedureCategoryRequestDTO procedureCategoryRequestDTO) {
        ProcedureCategoryResponseDTO procedureCategoryResponseDTO = procedureCategoryService.create(procedureCategoryRequestDTO);
        ApiResponse<ProcedureCategoryResponseDTO> responseDTO = ApiResponse
                .<ProcedureCategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(procedureCategoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ProcedureCategoryRequestDTO procedureCategoryRequestDTO) {
        ProcedureCategoryResponseDTO procedureCategoryResponseDTO = procedureCategoryService.update(id, procedureCategoryRequestDTO);
        ApiResponse<ProcedureCategoryResponseDTO> responseDTO = ApiResponse
                .<ProcedureCategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(procedureCategoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProcedureFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        ProcedureCategoryResponseDTO procedureCategoryResponseDTO = procedureCategoryService.updateProcedureFields(id, fields);
        ApiResponse<ProcedureCategoryResponseDTO> responseDTO = ApiResponse
                .<ProcedureCategoryResponseDTO>builder()
                .status("SUCCESS")
                .data(procedureCategoryResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        procedureCategoryService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("Procedure deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}