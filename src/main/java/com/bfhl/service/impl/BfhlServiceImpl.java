package com.bfhl.service.impl;

import com.bfhl.dto.BfhlRequestDTO;
import com.bfhl.dto.BfhlResponseDTO;
import com.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "karuna_jain_02082005";
    private static final String EMAIL = "Karunajain230183@acropolis.in";
    private static final String ROLL_NUMBER = "0827CS231125";

    @Override
    public BfhlResponseDTO processRequest(BfhlRequestDTO request) {
        List<String> data = request.data();
        
        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        
        long sum = 0;
        StringBuilder originalAlphabets = new StringBuilder();

        if (data != null) {
            for (String item : data) {
                if (item == null) continue;
                
                String trimmed = item.trim();
                if (trimmed.isEmpty()) continue;

                if (isNumeric(trimmed)) {
                    try {
                        long value = Long.parseLong(trimmed);
                        sum += value;
                        if (value % 2 == 0) {
                            evenNumbers.add(trimmed);
                        } else {
                            oddNumbers.add(trimmed);
                        }
                    } catch (NumberFormatException e) {
                        specialCharacters.add(trimmed);
                    }
                } else if (isAlphabet(trimmed)) {
                    alphabets.add(trimmed.toUpperCase());
                    originalAlphabets.append(trimmed);
                } else {
                    specialCharacters.add(trimmed);
                }
            }
        }

        String concatString = generateConcatString(originalAlphabets.toString());

        return new BfhlResponseDTO(
            true,
            USER_ID,
            EMAIL,
            ROLL_NUMBER,
            evenNumbers,
            oddNumbers,
            alphabets,
            specialCharacters,
            String.valueOf(sum),
            concatString
        );
    }

    private boolean isNumeric(String str) {
        if (str.isEmpty()) return false;
        return str.matches("^[+-]?\\d+$");
    }

    private boolean isAlphabet(String str) {
        return str.matches("^[a-zA-Z]+$");
    }

    private String generateConcatString(String original) {
        if (original.isEmpty()) {
            return "";
        }
        String reversed = new StringBuilder(original).reverse().toString();
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
