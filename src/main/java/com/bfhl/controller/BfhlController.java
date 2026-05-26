package com.bfhl.controller;

import com.bfhl.dto.BfhlRequestDTO;
import com.bfhl.dto.BfhlResponseDTO;
import com.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    // POST API
    @PostMapping
    public ResponseEntity<BfhlResponseDTO> processData(
            @Valid @RequestBody BfhlRequestDTO request) {

        BfhlResponseDTO response = bfhlService.processRequest(request);
        return ResponseEntity.ok(response);
    }

    // GET API
    @GetMapping
    public ResponseEntity<Map<String, Object>> getOperationCode() {

        return ResponseEntity.ok(
                Map.of("operation_code", 1));
    }

    // HEALTH API
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {

        return ResponseEntity.ok(
                Map.of(
                        "status", "UP",
                        "message", "API is running successfully"));
    }
}