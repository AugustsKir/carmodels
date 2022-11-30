package com.work.carmodels.cars;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.carmodels.cars.domain.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CarsControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CarRepository carRepository;
    @MockBean
    CarService carService;


    Car CAR_1 = new Car("BMW", "330i", 2002, "PCVS884338ADAD", "AA1122");
    Car CAR_2 = new Car("BMW", "335i", 2007, "WERS32423DRSEER", "BB2313");
    Car CAR_3 = new Car("Audi", "A4", 2012, "EEASDS432FRDD432", "KO8812");
    Car CAR_4 = new Car("Honda", "Accord", 2009, "EE912312EDJ99", "ZE9012");
    Car CAR_5 = new Car("Mitsubishi", "Eclipse", 2001, "IUREWR4324JJW14EE", "IJ2912");
    Car CAR_6 = new Car("Mitsubishi", "Eclipse", 2004, "IUREWR4324JJW14EE", "EA4718");

    List<Car> cars = new ArrayList<>(Arrays.asList(CAR_1, CAR_2, CAR_3, CAR_4, CAR_5));


    @Test
    @Description("Return the correct list of cars")
    public void getAllCars() throws Exception {

        Mockito.when(carService.exportContents()).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cars/export")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[1].brand", is("BMW")));
    }

    @Test
    @Description("Find cars by brand")
    public void findCarsByBrand() throws Exception {
        List<Car> res = new ArrayList<>(Collections.singletonList(CAR_4));
        Mockito.when(carService.findCars("honda")).thenReturn(res);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cars/{brand}", "honda")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        Assertions.assertEquals(302, mvcResult.getResponse().getStatus());
        List<Car> output = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Car>>() {
        });
        Assertions.assertEquals(res, output);
    }

    @Test
    @Description("Successfully add cars")
    public void canAddCars() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/cars/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(CAR_6)))
                .andReturn();
        Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    @Description("Can import custom data")
    public void importSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/cars/import")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(cars)))
                .andReturn();
        Assertions.assertEquals(201, mvcResult.getResponse().getStatus());
    }
}
