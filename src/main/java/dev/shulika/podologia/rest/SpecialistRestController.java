package dev.shulika.podologia.rest;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.SpecialistRequestDTO;
import dev.shulika.podologia.dto.SpecialistResponseDTO;
import dev.shulika.podologia.service.impl.SpecialistServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/specialists")
@RequiredArgsConstructor
public class SpecialistRestController {
    private final SpecialistServiceImpl specialistService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<SpecialistResponseDTO> specialists = specialistService.findAll();
        ApiResponse<List<SpecialistResponseDTO>> responseDTO = ApiResponse
                .<List<SpecialistResponseDTO>>builder()
                .status("SUCCESS")
                .results(specialists)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        SpecialistResponseDTO specialistResponseDTO = specialistService.findById(id);
        ApiResponse<SpecialistResponseDTO> responseDTO = ApiResponse
                .<SpecialistResponseDTO>builder()
                .status("SUCCESS")
                .results(specialistResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SpecialistRequestDTO specialistRequestDTO) {
        specialistService.create(specialistRequestDTO);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .results("Specialist created")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid SpecialistRequestDTO specialistRequestDTO) {
        specialistService.update(id, specialistRequestDTO);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .results("Specialist updated (PUT)")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSpecialistFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        specialistService.updateSpecialistFields(id, fields);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .results("Specialist updated (PATCH)")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        specialistService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .results("Specialist deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}