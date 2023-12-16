package com.example.supplychain.Controller;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.supplychain.controller.FacilityController;
import com.example.supplychain.model.FacilityAddress;
import com.example.supplychain.model.Facility;
import com.example.supplychain.model.Supplier;
import com.example.supplychain.service.FacilityServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = FacilityController.class)
public class FacilityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    @SpyBean
    private FacilityServiceInterface service;

    @Test
    void testThatCanFacilitycanbeGetUsingId() throws Exception {

        Mockito.when(service.getById(Mockito.anyString())).thenReturn(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build()));

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/facility/select/cd")).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        Facility op = new ObjectMapper().readValue(result, Facility.class);
        // .andReturn().getResponse().getContent;
        assertFalse(op.equals(null));

    }

    @Test
    void testDeleteWorks() throws Exception {
        // Mockito.when(service.getById(Mockito.anyString())).thenReturn(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),new ArrayList<String>(Arrays.asList("aa"))));

        Mockito.when(service.deleteData("cd")).thenReturn(true);
        // (new Facility("ab","Sai",new Address("aa","aa","aa","aa","aa"), new Supplier("aa","ss",new Address("aa","aa","aa","aa","aa"),"aa","aa","aa","aa","aa","aa"),new ArrayList<String>(Arrays.asList("aa"))));
        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/facility/delete/ab")).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        Boolean op = new ObjectMapper().readValue(result, Boolean.class);
        // .andReturn().getResponse().getContent;
        assertFalse(op.equals(null));

    }

    @Test
    void testDeleteNotWorks() throws Exception {
        Mockito.when(service.deleteData("ab")).thenReturn(true);
        // (new Facility("ab","Sai",new Address("aa","aa","aa","aa","aa"), new Supplier("aa","ss",new Address("aa","aa","aa","aa","aa"),"aa","aa","aa","aa","aa","aa"),new ArrayList<String>(Arrays.asList("aa"))));
        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/facility/delete/ab")).andExpect(status().isNotFound()).andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        Boolean op = new ObjectMapper().readValue(result, Boolean.class);
        // .andReturn().getResponse().getContent;
        assertFalse(op.equals(null));

    }

    @Test
    public void testThatSupplierCanBeSavedCorrectly() throws Exception{
        
        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        
        Mockito.when(service.saveData(Mockito.any(Facility.class))).thenReturn(facility);
        

        String result = mockMvc.perform(MockMvcRequestBuilders
                                        .post("/facility/save").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(facility)))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(facility)))
                                        .andReturn().getResponse()
                                        .getContentAsString();
                                        System.out.println("_____");
                                        System.out.println(result);
                                        System.out.println("_____");

         Facility actualOutput = new ObjectMapper().readValue(result, Facility.class);
            
        Assertions.assertThat(actualOutput).isEqualTo(facility);
    }

     @Test
    public void testThatFacilityCanbeUpdatedSuccessfully() throws JsonProcessingException, UnsupportedEncodingException, Exception
    {
        Facility expectedOutput = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        
        Mockito.when(service.updateData(Mockito.any(Facility.class))).thenReturn(expectedOutput);

        String result = mockMvc.perform(MockMvcRequestBuilders
                                        .put("/facility/update").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(expectedOutput)))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(expectedOutput)))
                                        .andReturn().getResponse().getContentAsString();
        
        String exOutput = new ObjectMapper().writeValueAsString(expectedOutput);
        Assertions.assertThat(result).isEqualTo(exOutput);
    }
}
