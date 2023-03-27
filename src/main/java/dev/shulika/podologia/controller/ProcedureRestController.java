package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.procedure.ProcedureRequestDTO;
import dev.shulika.podologia.dto.procedure.ProcedureResponseDTO;
import dev.shulika.podologia.service.ProcedureService;
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
@RequestMapping("/api/v1/procedures")
@RequiredArgsConstructor
@Tag(name = "Procedure", description = "Contains simple information about procedure: specialist info only id, procedure name ")
public class ProcedureRestController {
    private final ProcedureService procedureService;

    @GetMapping
    @Operation(summary = "Get all procedures", description = "Get all procedures")
    public ResponseEntity<?> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<ProcedureResponseDTO> procedures = procedureService.findAll(pageable);
        ApiResponse<List<ProcedureResponseDTO>> responseDTO = ApiResponse
                .<List<ProcedureResponseDTO>>builder()
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
    @Operation(summary = "Get procedure by id", description = "Get procedure by id")
    public ResponseEntity<?> findById(@PathVariable long id) {
        ProcedureResponseDTO procedureResponseDTO = procedureService.findById(id);
        ApiResponse<ProcedureResponseDTO> responseDTO = ApiResponse
                .<ProcedureResponseDTO>builder()
                .status("SUCCESS")
                .data(procedureResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create procedure", description = "Create procedure")
    public ResponseEntity<?> create(@RequestBody @Valid ProcedureRequestDTO procedureRequestDTO) {
        ProcedureResponseDTO procedureResponseDTO = procedureService.create(procedureRequestDTO);
        ApiResponse<ProcedureResponseDTO> responseDTO = ApiResponse
                .<ProcedureResponseDTO>builder()
                .status("SUCCESS")
                .data(procedureResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit procedure: Put", description = "Edit procedure: Put")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ProcedureRequestDTO procedureRequestDTO) {
        ProcedureResponseDTO procedureResponseDTO = procedureService.update(id, procedureRequestDTO);
        ApiResponse<ProcedureResponseDTO> responseDTO = ApiResponse
                .<ProcedureResponseDTO>builder()
                .status("SUCCESS")
                .data(procedureResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edit procedure: Patch", description = "Edit procedure: Patch")
    public ResponseEntity<?> updateProcedureFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        ProcedureResponseDTO procedureResponseDTO = procedureService.updateProcedureFields(id, fields);
        ApiResponse<ProcedureResponseDTO> responseDTO = ApiResponse
                .<ProcedureResponseDTO>builder()
                .status("SUCCESS")
                .data(procedureResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete procedure", description = "Delete procedure")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        procedureService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("Procedure deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}