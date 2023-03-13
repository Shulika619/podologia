package dev.shulika.podologia.rest;


import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.ProcedureRequestDTO;
import dev.shulika.podologia.model.Price;
import dev.shulika.podologia.repository.PriceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceRestController {
    private final PriceRepository priceRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(priceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
            return new ResponseEntity<>(priceRepository.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Price price) {
        return new ResponseEntity<>(priceRepository.save(price), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Price price) {
        return new ResponseEntity<>(priceRepository.save(price), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        priceRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}