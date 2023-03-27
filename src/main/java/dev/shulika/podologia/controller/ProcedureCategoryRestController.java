package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryRequestDTO;
import dev.shulika.podologia.dto.procedureCategory.ProcedureCategoryResponseDTO;
import dev.shulika.podologia.service.ProcedureCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Procedure with Category", description = "Contains full information about procedure with category: specialist full info, procedure name ")
public class ProcedureCategoryRestController {
    private final ProcedureCategoryService procedureCategoryService;

    @GetMapping
    @Operation(summary = "Get all procedures with categories", description = "Get all procedures with categories")
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
    @Operation(summary = "Get procedure with category by id", description = "Get procedure with category by id")
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
    @Operation(summary = "Create procedure and category", description = "Create procedure and category")
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
    @Operation(summary = "Edit procedure and category: Put", description = "Edit procedure and category: Put")
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
    @Operation(summary = "Edit procedure and category: Patch", description = "Edit procedure and category: Patch")
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
    @Operation(summary = "Delete procedure", description = "Delete procedure")
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