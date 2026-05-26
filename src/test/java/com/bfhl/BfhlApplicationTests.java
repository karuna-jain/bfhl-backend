package com.bfhl;

import com.bfhl.dto.BfhlRequestDTO;
import com.bfhl.dto.BfhlResponseDTO;
import com.bfhl.service.BfhlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BfhlApplicationTests {

    @Autowired
    private BfhlService bfhlService;

    @Test
    void contextLoads() {
        assertNotNull(bfhlService);
    }

    @Test
    void testProcessRequest_SuccessMixed() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("a", "1", "334", "$", "y", "B"));
        BfhlResponseDTO response = bfhlService.processRequest(request);

        assertTrue(response.is_success());
        assertEquals("karuna_jain_02082005", response.user_id());
        assertEquals("Karunajain230183@acropolis.in", response.email());
        assertEquals("0827CS231125", response.roll_number());

        // Numbers: 1 (odd), 334 (even). Sum = 335
        assertEquals(List.of("334"), response.even_numbers());
        assertEquals(List.of("1"), response.odd_numbers());
        assertEquals("335", response.sum());

        // Alphabets: a, y, B -> uppercase list: A, Y, B
        assertEquals(Arrays.asList("A", "Y", "B"), response.alphabets());

        // Special: $
        assertEquals(List.of("$"), response.special_characters());

        // Concat string: alphabets are a, y, B -> reversed: Bya -> alternating caps: ByA
        assertEquals("ByA", response.concat_string());
    }

    @Test
    void testProcessRequest_AllNumbers() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("2", "4", "5", "10"));
        BfhlResponseDTO response = bfhlService.processRequest(request);

        assertTrue(response.is_success());
        assertEquals(Arrays.asList("2", "4", "10"), response.even_numbers());
        assertEquals(List.of("5"), response.odd_numbers());
        assertEquals("21", response.sum());
        assertTrue(response.alphabets().isEmpty());
        assertTrue(response.special_characters().isEmpty());
        assertEquals("", response.concat_string());
    }

    @Test
    void testProcessRequest_AllAlphabets() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("x", "Y", "z"));
        BfhlResponseDTO response = bfhlService.processRequest(request);

        assertTrue(response.is_success());
        assertTrue(response.even_numbers().isEmpty());
        assertTrue(response.odd_numbers().isEmpty());
        assertEquals("0", response.sum());
        assertEquals(Arrays.asList("X", "Y", "Z"), response.alphabets());
        // Alphabets: xYz -> reversed: zYx -> alternating caps starting with caps: ZyX
        assertEquals("ZyX", response.concat_string());
    }

    @Test
    void testProcessRequest_EmptyData() {
        BfhlRequestDTO request = new BfhlRequestDTO(Collections.emptyList());
        BfhlResponseDTO response = bfhlService.processRequest(request);

        assertTrue(response.is_success());
        assertTrue(response.even_numbers().isEmpty());
        assertTrue(response.odd_numbers().isEmpty());
        assertEquals("0", response.sum());
        assertTrue(response.alphabets().isEmpty());
        assertTrue(response.special_characters().isEmpty());
        assertEquals("", response.concat_string());
    }

    @Test
    void testProcessRequest_SpecialCharacters() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("@", "#", "$$"));
        BfhlResponseDTO response = bfhlService.processRequest(request);

        assertTrue(response.is_success());
        assertTrue(response.even_numbers().isEmpty());
        assertTrue(response.odd_numbers().isEmpty());
        assertEquals("0", response.sum());
        assertTrue(response.alphabets().isEmpty());
        assertEquals(Arrays.asList("@", "#", "$$"), response.special_characters());
        assertEquals("", response.concat_string());
    }
}
