package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.specialist.SpecialistRequestDTO;
import dev.shulika.podologia.dto.specialist.SpecialistResponseDTO;
import dev.shulika.podologia.service.SpecialistService;
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
@RequestMapping("/api/v1/specialists")
@RequiredArgsConstructor
@Tag(name = "Specialist", description = "Specialists for procedures, contains all the operations that can be performed on specialist")
public class SpecialistRestController {
    private final SpecialistService specialistService;

    @GetMapping
    @Operation(summary = "Get all specialists", description = "Get all specialists")
    public ResponseEntity<?> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<SpecialistResponseDTO> specialists = specialistService.findAll(pageable);
        ApiResponse<List<SpecialistResponseDTO>> responseDTO = ApiResponse
                .<List<SpecialistResponseDTO>>builder()
                .status("SUCCESS")
                .data(specialists.getContent())
                .totalElements(specialists.getTotalElements())
                .perPage(specialists.getSize())
                .currentPage(specialists.getNumber())
                .totalPages(specialists.getTotalPages())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specialist by id", description = "Get specialist by id")
    public ResponseEntity<?> findById(@PathVariable long id) {
        SpecialistResponseDTO specialistResponseDTO = specialistService.findById(id);
        ApiResponse<SpecialistResponseDTO> responseDTO = ApiResponse
                .<SpecialistResponseDTO>builder()
                .status("SUCCESS")
                .data(specialistResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create specialist", description = "Create specialist")
    public ResponseEntity<?> create(@RequestBody @Valid SpecialistRequestDTO specialistRequestDTO) {
        SpecialistResponseDTO specialistResponseDTO = specialistService.create(specialistRequestDTO);
        ApiResponse<SpecialistResponseDTO> responseDTO = ApiResponse
                .<SpecialistResponseDTO>builder()
                .status("SUCCESS")
                .data(specialistResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit specialist: Put", description = "Edit specialist: Put")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid SpecialistRequestDTO specialistRequestDTO) {
        SpecialistResponseDTO specialistResponseDTO = specialistService.update(id, specialistRequestDTO);
        ApiResponse<SpecialistResponseDTO> responseDTO = ApiResponse
                .<SpecialistResponseDTO>builder()
                .status("SUCCESS")
                .data(specialistResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edit specialist: Patch", description = "Edit specialist: Patch")
    public ResponseEntity<?> updateSpecialistFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        SpecialistResponseDTO specialistResponseDTO = specialistService.updateSpecialistFields(id, fields);
        ApiResponse<SpecialistResponseDTO> responseDTO = ApiResponse
                .<SpecialistResponseDTO>builder()
                .status("SUCCESS")
                .data(specialistResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete specialist", description = "Delete specialist")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        specialistService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("Specialist deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}