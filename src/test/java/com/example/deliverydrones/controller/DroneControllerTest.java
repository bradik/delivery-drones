package com.example.deliverydrones.controller;

import com.example.deliverydrones.DeliveryDronesApplication;
import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.deliverydrones.util.Constants.API;
import static com.example.deliverydrones.util.Constants.DRONES;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DroneControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void registerDrone() throws Exception {

        DroneDto droneDto = DroneDto.builder().serialNumber("MQ198GT").build();

        String json = JsonUtils.objectToJson(droneDto);
        mockMvc.perform(post(API + DRONES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

    }
}