package com.bfhl.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record BfhlRequestDTO(
    @NotNull(message = "data list cannot be null")
    List<String> data
) {}
