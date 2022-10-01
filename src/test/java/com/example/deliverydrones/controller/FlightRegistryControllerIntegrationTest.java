package com.example.deliverydrones.controller;

import com.example.deliverydrones.dto.FlightRegistryDto;
import com.example.deliverydrones.dto.MedicationDto;
import com.example.deliverydrones.dto.MedicationItemDto;
import com.example.deliverydrones.service.FlightRegistryService;
import com.example.deliverydrones.service.MedicationService;
import com.example.deliverydrones.util.JsonUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static com.example.deliverydrones.util.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FlightRegistryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private FlightRegistryService flightRegistryService;

    @Test
    void registerFlight() throws Exception {

        List<MedicationDto> medication = medicationService.getAllMedication(1, 5);

        String json = JsonUtils.objectToJson(List.of(
                getMedicationItemDto(1, medication.get(0)),
                getMedicationItemDto(2, medication.get(1))
        ));

        String serialNumber = "HW423G3"; //500g

        String respString =
                mockMvc.perform(
                                post(API + FLIGHTS + "/register/drone/" + serialNumber)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(json))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        FlightRegistryDto flightRegistryDto = JsonUtils.jsonToObject(respString, FlightRegistryDto.class);

        assertEquals(serialNumber, flightRegistryDto.getDrone().getSerialNumber());
    }

    @Test
    void addMedicationToFlight() throws Exception {

        List<MedicationDto> medication = medicationService.getAllMedication(1, 5);

        List<MedicationItemDto> dtoList1 = Arrays.asList(
                getMedicationItemDto(1, medication.get(0)),
                getMedicationItemDto(2, medication.get(1))
        );

        List<MedicationItemDto> dtoList2 = Arrays.asList(
                getMedicationItemDto(1, medication.get(1)),
                getMedicationItemDto(5, medication.get(2))
        );

        String json = JsonUtils.objectToJson(dtoList2);

        String serialNumber = "HW678G2"; //500g

        flightRegistryService.registerFlight(serialNumber, dtoList1);

        String respString =
                mockMvc.perform(
                                post(API + FLIGHTS + "/medication/add/drone/" + serialNumber)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(json))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        FlightRegistryDto flightRegistryDto = JsonUtils.jsonToObject(respString, FlightRegistryDto.class);

        assertEquals(serialNumber, flightRegistryDto.getDrone().getSerialNumber());

        Assertions.assertThat(flightRegistryDto.getMedicationItems())
                .anyMatch(it -> it.getCount() == 1)
                .anyMatch(it -> it.getCount() == 3)
                .anyMatch(it -> it.getCount() == 5)
        ;
    }

    @Test
    void getMedicationItemsBySerialNumber() throws Exception {

        List<MedicationDto> medication = medicationService.getAllMedication(1, 5);

        List<MedicationItemDto> dtoList1 = Arrays.asList(
                getMedicationItemDto(1, medication.get(0)),
                getMedicationItemDto(2, medication.get(1))
        );

        String serialNumber = "HW567G1"; //500g

        flightRegistryService.registerFlight(serialNumber, dtoList1);

        String respString =
                mockMvc.perform(
                                get(API + FLIGHTS + "/medication/drone/" + serialNumber))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        MedicationItemDto[] medicationItemDtos = JsonUtils.jsonToObject(respString, MedicationItemDto[].class);

        List<MedicationItemDto> medicationItemDtoList = Arrays.asList(medicationItemDtos);

        Assertions.assertThat(medicationItemDtoList)
                .anyMatch(it -> it.getCount() == 1)
                .anyMatch(it -> it.getCount() == 2)
        ;
    }

    private MedicationItemDto getMedicationItemDto(int count, MedicationDto dto) {
        return MedicationItemDto
                .builder()
                .medication(dto)
                .count(count)
                .build();
    }
}