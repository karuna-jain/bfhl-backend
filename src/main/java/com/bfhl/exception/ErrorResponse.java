package com.bfhl.exception;

public record ErrorResponse(
    boolean is_success,
    String message
) {}
