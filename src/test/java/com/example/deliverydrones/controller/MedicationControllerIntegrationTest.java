package com.example.deliverydrones.controller;

import com.example.deliverydrones.dto.MedicationDto;
import com.example.deliverydrones.util.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.deliverydrones.util.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MedicationControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void registerMedication() throws Exception {

        MedicationDto dto = MedicationDto
                .builder()
                .code("CD197")
                .name("drug-1")
                .weight(1)
                .build();

        String json = JsonUtils.objectToJson(dto);
        String respString =
                mockMvc.perform(post(API + MEDICATIONS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        MedicationDto actual = JsonUtils.jsonToObject(respString, MedicationDto.class);

        assertNotNull(actual.getId());
        assertEquals("CD197", actual.getCode());
    }

    @Test
    void getAllMedications() throws Exception {
        String respString =
                mockMvc.perform(
                                get(API + MEDICATIONS + "/all")
                                        .param("pageNumber", "1").param("pageSize", "5"))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        MedicationDto[] medicationDtos = JsonUtils.jsonToObject(respString, MedicationDto[].class);

        assertEquals(5 , medicationDtos.length);
    }
}