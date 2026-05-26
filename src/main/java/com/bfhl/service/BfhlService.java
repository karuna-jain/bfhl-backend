package com.bfhl.service;

import com.bfhl.dto.BfhlRequestDTO;
import com.bfhl.dto.BfhlResponseDTO;

public interface BfhlService {
    BfhlResponseDTO processRequest(BfhlRequestDTO request);
}
