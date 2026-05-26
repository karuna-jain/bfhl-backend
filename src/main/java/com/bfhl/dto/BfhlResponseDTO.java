package com.bfhl.dto;

import java.util.List;

public record BfhlResponseDTO(
    boolean is_success,
    String user_id,
    String email,
    String roll_number,
    List<String> even_numbers,
    List<String> odd_numbers,
    List<String> alphabets,
    List<String> special_characters,
    String sum,
    String concat_string
) {}
