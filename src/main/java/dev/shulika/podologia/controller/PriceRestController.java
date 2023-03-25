package dev.shulika.podologia.controller;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.price.PriceRequestDTO;
import dev.shulika.podologia.dto.price.PriceResponseDTO;
import dev.shulika.podologia.service.PriceService;
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
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceRestController {
    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<?> findAllByPage(@PageableDefault(size = 10) Pageable pageable) {
        Page<PriceResponseDTO> prices = priceService.findAllByPage(pageable);
        ApiResponse<List<PriceResponseDTO>> responseDTO = ApiResponse
                .<List<PriceResponseDTO>>builder()
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
    public ResponseEntity<?> findById(@PathVariable long id) {
        PriceResponseDTO priceResponseDTO = priceService.findById(id);
        ApiResponse<PriceResponseDTO> responseDTO = ApiResponse
                .<PriceResponseDTO>builder()
                .status("SUCCESS")
                .data(priceResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PriceRequestDTO priceRequestDTO) {
        PriceResponseDTO priceResponseDTO = priceService.create(priceRequestDTO);
        ApiResponse<PriceResponseDTO> responseDTO = ApiResponse
                .<PriceResponseDTO>builder()
                .status("SUCCESS")
                .data(priceResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid PriceRequestDTO priceRequestDTO) {
        PriceResponseDTO priceResponseDTO = priceService.update(id, priceRequestDTO);
        ApiResponse<PriceResponseDTO> responseDTO = ApiResponse
                .<PriceResponseDTO>builder()
                .status("SUCCESS")
                .data(priceResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePriceFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        PriceResponseDTO priceResponseDTO = priceService.updateFields(id, fields);
        ApiResponse<PriceResponseDTO> responseDTO = ApiResponse
                .<PriceResponseDTO>builder()
                .status("SUCCESS")
                .data(priceResponseDTO)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        priceService.delete(id);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("Price deleted")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}