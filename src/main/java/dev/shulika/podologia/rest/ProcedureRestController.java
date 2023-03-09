package dev.shulika.podologia.rest;
import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.ProcedureRequestDTO;
import dev.shulika.podologia.dto.ProcedureResponseDTO;
import dev.shulika.podologia.service.impl.ProcedureServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/procedures")
@RequiredArgsConstructor
public class ProcedureRestController {
    private final ProcedureServiceImpl procedureService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ProcedureResponseDTO> procedures = procedureService.findAll();
        ApiResponse<List<ProcedureResponseDTO>> responseDTO = ApiResponse
                .<List<ProcedureResponseDTO>>builder()
                .status("SUCCESS")
                .results(procedures)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        ProcedureResponseDTO procedureResponseDTO = procedureService.findById(id);
        ApiResponse<ProcedureResponseDTO> responseDTO = ApiResponse
                .<ProcedureResponseDTO>builder()
                .status("SUCCESS")
                .results(procedureResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProcedureRequestDTO procedureRequestDTO) {
        procedureService.create(procedureRequestDTO);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .results("Procedure created")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ProcedureRequestDTO procedureRequestDTO) {
        procedureService.update(id, procedureRequestDTO);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .results("Procedure updated (PUT)")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProcedureFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        procedureService.updateProcedureFields(id, fields);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .results("Procedure updated (PATCH)")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        procedureService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .results("Procedure deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}