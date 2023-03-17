package dev.shulika.podologia.rest;

import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.price.PriceRequestDTO;
import dev.shulika.podologia.dto.price.PriceResponseDTO;
import dev.shulika.podologia.service.impl.PriceServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceRestController {
    private final PriceServiceImpl priceService;

    @GetMapping
    public ResponseEntity<?> findAllByPage(
            @RequestParam(required = false,defaultValue = "0")int page,
            @RequestParam(required = false,defaultValue = "4")int size
    ) {
        Page<PriceResponseDTO> prices = priceService.findAllByPage(PageRequest.of(page, size));
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
        priceService.create(priceRequestDTO);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("Price created")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid PriceRequestDTO priceRequestDTO) {
        priceService.update(id, priceRequestDTO);
        ApiResponse<String> responseDTO = ApiResponse
                .<String>builder()
                .status("SUCCESS")
                .data("Price updated (PUT)")
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