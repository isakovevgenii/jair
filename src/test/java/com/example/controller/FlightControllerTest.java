package com.example.controller;

import com.example.entity.model.Flight;
import com.example.service.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FlightControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Test
    public void getAllFlightTest() throws Exception {

        Flight mockFlight1 = new Flight(
                1,
                "lufthansa",
                "airbasa350",
                "james doolittle",
                "robert hoover"
        );

        Flight mockFlight2 = new Flight(
                2,
                "swissair",
                "boeing787",
                "robert hoover",
                "james doolittle"
        );

        List<Flight> flightList = new ArrayList<>();
        flightList.add(mockFlight1);
        flightList.add(mockFlight2);

        String expected = "" +
                "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"airline\": \"lufthansa\",\n" +
                "        \"airplane\": \"airbasa350\",\n" +
                "        \"pilot\": \"james doolittle\",\n" +
                "        \"copilot\": \"robert hoover\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"airline\": \"swissair\",\n" +
                "        \"airplane\": \"boeing787\",\n" +
                "        \"pilot\": \"robert hoover\",\n" +
                "        \"copilot\": \"james doolittle\"\n" +
                "    }" +
                "]" +
                "";

        Mockito.when(
                flightService.getAllFlight()).thenReturn(ResponseEntity.ok(flightList));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/flight/all")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void createFlight() throws Exception {

        Flight mockFlight = new Flight(
                1,
                "lufthansa",
                "airbasa350",
                "james doolittle",
                "robert hoover"
        );

        String exampleFlightJson = "" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"airline\": \"lufthansa\",\n" +
                "        \"airplane\": \"airbasa350\",\n" +
                "        \"pilot\": \"james doolittle\",\n" +
                "        \"copilot\": \"robert hoover\"\n" +
                "    }" +
                "";

        String expected = "" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"airline\": \"lufthansa\",\n" +
                "        \"airplane\": \"airbasa350\",\n" +
                "        \"pilot\": \"james doolittle\",\n" +
                "        \"copilot\": \"robert hoover\"\n" +
                "    }" +
                "";

        Mockito.when(
                flightService.saveFlight(
                        Mockito.any(Flight.class))).thenReturn(ResponseEntity.ok(mockFlight));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/flight/add")
                .accept(MediaType.APPLICATION_JSON).content(exampleFlightJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}
