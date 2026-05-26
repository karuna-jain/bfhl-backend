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

    @PostMapping
    public ResponseEntity<BfhlResponseDTO> processData(@Valid @RequestBody BfhlRequestDTO request) {
        BfhlResponseDTO response = bfhlService.processRequest(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getOperationCode() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "user_id", "karuna_jain_02082005"));
    }
}