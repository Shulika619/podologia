package dev.shulika.podologia.rest;
import dev.shulika.podologia.dto.ApiResponse;
import dev.shulika.podologia.dto.CategoryResponseDTO;
import dev.shulika.podologia.model.Category;
import dev.shulika.podologia.model.Procedure;
import dev.shulika.podologia.service.ProcedureService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/procedures")
@RequiredArgsConstructor
public class ProcedureRestController {
    private final ProcedureService procedureService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Procedure> procedures = procedureService.findAll();
//        ApiResponse<List<Procedure>> responseDTO = ApiResponse
//                .<List<CategoryResponseDTO>>builder()
//                .status("SUCCESS")
//                .results(procedures)
//                .build();

        return new ResponseEntity<>(procedures, HttpStatus.OK);
    }

}