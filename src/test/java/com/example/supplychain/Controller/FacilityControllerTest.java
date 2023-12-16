package com.example.supplychain.Controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.supplychain.controller.FacilityController;
import com.example.supplychain.model.Facility;
import com.example.supplychain.model.Supplier;
import com.example.supplychain.service.FacilityServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = FacilityController.class)
public class FacilityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FacilityServiceInterface service;

    @Test
    public void testThatFacilityCanbeGetFromUsingId() throws Exception
    {

        Facility expectedOutput = Facility.builder() 
            ._id("657c139f3f6f0767f7ce55a9")
            .facilityName("ABC")
            .facilityAddress("coimbatore")
            .supplierId(Supplier.builder()._id("657ad304dc612c31ad07aef2").build())
            .build();

        Mockito.when(service.getById(Mockito.anyString())).thenReturn(expectedOutput);
            
          String result = mockMvc.perform(MockMvcRequestBuilders
                                        .get("/facility/select/id")).andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn().getResponse()
                                        .getContentAsString();

                                        System.out.println("___________");
                                        System.out.println(result);
                                        System.out.println("___________");

        Facility actualOutput = new ObjectMapper().readValue(result, Facility.class);
            
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);

    }
    

}
