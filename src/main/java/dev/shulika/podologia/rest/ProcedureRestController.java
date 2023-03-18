package dev.shulika.podologia.rest;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.procedure.ProcedureRequestDTO;
import dev.shulika.podologia.dto.procedure.ProcedureResponseDTO;
import dev.shulika.podologia.service.ProcedureService;
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
public class ProcedureRestController {
    private final ProcedureService procedureService;

    @GetMapping
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