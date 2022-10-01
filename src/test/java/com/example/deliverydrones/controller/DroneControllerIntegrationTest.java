package com.example.deliverydrones.controller;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.entity.DroneModel;
import com.example.deliverydrones.entity.DroneState;
import com.example.deliverydrones.util.JsonUtils;
import org.junit.jupiter.api.Assertions;
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
class DroneControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerFlight() throws Exception {

        String serialNumber = "MQ198GT";
        DroneDto droneDto = DroneDto.builder()
                .serialNumber(serialNumber)
                .model(DroneModel.Cruiserweight)
                .state(DroneState.IDLE)
                .batteryCapacity(100)
                .weightLimit(500)
                .build();

        String json = JsonUtils.objectToJson(droneDto);
        String respString =
                mockMvc.perform(post(API + DRONES)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        DroneDto actual = JsonUtils.jsonToObject(respString, DroneDto.class);

        assertNotNull(actual.getId());
        assertEquals(serialNumber, actual.getSerialNumber());
    }

    @Test
    void availableForLoading() throws Exception {

        String respString =
                mockMvc.perform(
                                get(API + DRONES + "/availableForLoading")
                                        .param("pageNumber", "1").param("pageSize", "15"))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        DroneDto[] drones = JsonUtils.jsonToObject(respString, DroneDto[].class);

        assertTrue(drones.length > 0);

    }

    @Test
    void getBatteryLevel() throws Exception {

        String serialNumber = "LW198G1";

        String respString =
                mockMvc.perform(
                                get(API + DRONES + "/battery/" + serialNumber))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        Assertions.assertEquals(24, Integer.parseInt(respString));
    }

}