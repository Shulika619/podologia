package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoRequestDTO;
import dev.shulika.podologia.dto.priceFullInfo.PriceFullInfoResponseDTO;
import dev.shulika.podologia.service.PriceFullInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/v1/prices-full-info")
@RequiredArgsConstructor
@Tag(name = "Price Full Info", description = "Contains more information: procedure full info, specialist full info, price, time")
@SecurityRequirement(name = "bearerAuth")
public class PriceFullInfoRestController {
    private final PriceFullInfoService priceFullInfoService;

    @GetMapping
    @Operation(summary = "Get all prices with procedures and specialists", description = "Get all prices with procedures and specialists")
    public ResponseEntity<?> findAllByPage(@PageableDefault(size = 10) Pageable pageable) {
        Page<PriceFullInfoResponseDTO> prices = priceFullInfoService.findAllByPage(pageable);
        ApiResponse<List<PriceFullInfoResponseDTO>> responseDTO = ApiResponse
                .<List<PriceFullInfoResponseDTO>>builder()
                .status("SUCCESS")
                .data(prices.getContent())
                .totalElements(prices.getTotalElements())
                .perPage(prices.getSize())
                .currentPage(prices.getNumber())
                .totalPages(prices.getTotalPages())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get price with procedure and specialist by id", description = "Get price with procedure and specialist by id")
    public ResponseEntity<?> findById(@PathVariable long id) {
        PriceFullInfoResponseDTO priceResponseDTO = priceFullInfoService.findById(id);
        ApiResponse<PriceFullInfoResponseDTO> responseDTO = ApiResponse
                .<PriceFullInfoResponseDTO>builder()
                .status("SUCCESS")
                .data(priceResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create price, procedure and specialist", description = "Create price, procedure and specialist")
    public ResponseEntity<?> create(@RequestBody @Valid PriceFullInfoRequestDTO priceFullInfoRequestDTO) {
        PriceFullInfoResponseDTO priceFullInfoResponseDTO = priceFullInfoService.create(priceFullInfoRequestDTO);
        ApiResponse<PriceFullInfoResponseDTO> responseDTO = ApiResponse
                .<PriceFullInfoResponseDTO>builder()
                .status("SUCCESS")
                .data(priceFullInfoResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit price with procedure and specialist: Put", description = "Edit price with procedure and specialist: Put")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid PriceFullInfoRequestDTO priceFullInfoRequestDTO) {
        PriceFullInfoResponseDTO priceFullInfoResponseDTO = priceFullInfoService.update(id, priceFullInfoRequestDTO);
        ApiResponse<PriceFullInfoResponseDTO> responseDTO = ApiResponse
                .<PriceFullInfoResponseDTO>builder()
                .status("SUCCESS")
                .data(priceFullInfoResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Edit price with procedure and specialist: Patch", description = "Edit price with procedure and specialist: Patch")
    public ResponseEntity<?> updateFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        PriceFullInfoResponseDTO priceFullInfoResponseDTO = priceFullInfoService.updateFields(id, fields);
        ApiResponse<PriceFullInfoResponseDTO> responseDTO = ApiResponse
                .<PriceFullInfoResponseDTO>builder()
                .status("SUCCESS")
                .data(priceFullInfoResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete price", description = "Delete price")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        priceFullInfoService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("Price deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}