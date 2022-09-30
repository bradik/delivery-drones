package com.example.deliverydrones.controller;

import com.example.deliverydrones.dto.DroneDto;
import com.example.deliverydrones.dto.FlightRegistryDto;
import com.example.deliverydrones.dto.MedicationDto;
import com.example.deliverydrones.dto.MedicationItemDto;
import com.example.deliverydrones.entity.DroneModel;
import com.example.deliverydrones.entity.DroneState;
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

import java.util.List;

import static com.example.deliverydrones.util.Constants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        DroneDto droneDto = DroneDto.builder()
                .serialNumber("MQ198GT")
                .model(DroneModel.Cruiserweight)
                .state(DroneState.IDLE)
                .batteryCapacity(100)
                .weightLimit(500)
                .build();

        String json = JsonUtils.objectToJson(droneDto);
        mockMvc.perform(post(API + DRONES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        String respString = "";

        respString = mockMvc.perform(
                        get(API + DRONES + "/availableForLoading")
                                .param("pageNumber", "1").param("pageSize", "15"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DroneDto[] drones = JsonUtils.jsonToObject(respString, DroneDto[].class);

        String serialNumber = "MQ198GT";

        respString = mockMvc.perform(
                        get(API + DRONES + "/battery/" + serialNumber))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        respString = mockMvc.perform(
                        get(API + MEDICATIONS + "/all")
                                .param("pageNumber", "1").param("pageSize", "5"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        MedicationDto[] medicationDtos = JsonUtils.jsonToObject(respString, MedicationDto[].class);

        MedicationItemDto itemDto0 = MedicationItemDto.builder()
                .medication(medicationDtos[0])
                .count(1)
                .build();

        MedicationItemDto itemDto1 = MedicationItemDto.builder()
                .medication(medicationDtos[1])
                .count(2)
                .build();

        json = JsonUtils.objectToJson(List.of(itemDto0, itemDto1));

        respString = mockMvc.perform(
                        post(API + FLIGHTS + "/register/" + serialNumber)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        FlightRegistryDto flightRegistryDto = JsonUtils.jsonToObject(respString, FlightRegistryDto.class);

        Long flightId = flightRegistryDto.getId();

        MedicationItemDto itemDto2 = MedicationItemDto.builder()
                .medication(medicationDtos[2])
                .count(1)
                .build();

        json = JsonUtils.objectToJson(List.of(itemDto2, itemDto1));

        respString = mockMvc.perform(
                        post(API + FLIGHTS + "/medication/add/" + flightId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        flightRegistryDto = JsonUtils.jsonToObject(respString, FlightRegistryDto.class);


        respString = mockMvc.perform(
                        get(API + FLIGHTS + "/drone/" + serialNumber))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        flightRegistryDto = JsonUtils.jsonToObject(respString, FlightRegistryDto.class);

        System.out.println("");

    }
}