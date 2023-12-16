package com.example.supplychain.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.supplychain.controller.SupplierController;
import com.example.supplychain.model.Facility;
import com.example.supplychain.model.Supplier;
import com.example.supplychain.model.Supplier.Address;
import com.example.supplychain.service.SupplierServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = SupplierController.class)
public class SupplierControllerTest {
    
        @Autowired
    MockMvc mockMvc;

    @MockBean
    private SupplierServiceInterface service;

    @Test
    public void testThatSupplierCanbeGetFromUsingId() throws Exception
    {

        Supplier expectedOutput = Supplier.builder()
                ._id("")
                .supplierName("Sample Supplier")
                .supplierAddress(Address.builder()
                        .street("123 Main St")
                        .city("Cityville")
                        .pincode("12345")
                        .state("Stateville")
                        .country("Countryland")
                        .build())
                .supplierContact("123-456-7890")
                .supplierEmail("sample@supplier.com")
                .supplierWebsite("www.sample-supplier.com")
                .supplierTierNo("Tier 1")
                .rawMaterial("Sample Raw Material")
                .styles("Sample Styles")
                .build();

        Mockito.when(service.getById(Mockito.anyString())).thenReturn(expectedOutput);
            
        String result = mockMvc.perform(MockMvcRequestBuilders
                                        .get("/supplier/select/id")).andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn().getResponse()
                                        .getContentAsString();
                                        System.out.println("___________");
                                        System.out.println(result);
                                        System.out.println("___________");

        Supplier actualOutput = new ObjectMapper().readValue(result, Supplier.class);
            
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);

    }

    @Test
    public void testThatSupplierCanBeSavedCorrectly() throws Exception{
         
        Supplier expectedOutput = Supplier.builder()
                ._id("")
                .supplierName("Sample Supplier")
                .supplierAddress(Address.builder()
                        .street("123 Main St")
                        .city("Cityville")
                        .pincode("12345")
                        .state("Stateville")
                        .country("Countryland")
                        .build())
                .supplierContact("123-456-7890")
                .supplierEmail("sample@supplier.com")
                .supplierWebsite("www.sample-supplier.com")
                .supplierTierNo("Tier 1")
                .rawMaterial("Sample Raw Material")
                .styles("Sample Styles")
                .build();
        
        Mockito.when(service.saveData(Mockito.any(Supplier.class))).thenReturn(expectedOutput);

        String result = mockMvc.perform(MockMvcRequestBuilders
                                        .post("/supplier/save").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(expectedOutput)))
                                        .andExpect(MockMvcResultMatchers.status().isCreated())
                                        .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(expectedOutput)))
                                        .andReturn().getResponse()
                                        .getContentAsString();
                                        System.out.println("___________");
                                        System.out.println(result);
                                        System.out.println("___________");

         Supplier actualOutput = new ObjectMapper().readValue(result, Supplier.class);
            
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    public void testThatSupplierCanbeUpdatedSuccessfully() throws JsonProcessingException, UnsupportedEncodingException, Exception
    {
        Supplier expectedOutput = Supplier.builder()
                ._id("")
                .supplierName("Sample Supplier")
                .supplierAddress(Address.builder()
                        .street("123 Main St")
                        .city("Cityville")
                        .pincode("12345")
                        .state("Stateville")
                        .country("Countryland")
                        .build())
                .supplierContact("123-456-7890")
                .supplierEmail("sample@supplier.com")
                .supplierWebsite("www.sample-supplier.com")
                .supplierTierNo("Tier 1")
                .rawMaterial("Sample Raw Material")
                .styles("Sample Styles")
                .build();
        
        Mockito.when(service.updateData(Mockito.any(Supplier.class))).thenReturn(expectedOutput);

        String result = mockMvc.perform(MockMvcRequestBuilders
                                        .put("/supplier/update").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(expectedOutput)))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(expectedOutput)))
                                        .andReturn().getResponse().getContentAsString();
        
        String exOutput = new ObjectMapper().writeValueAsString(expectedOutput);
        Assertions.assertThat(result).isEqualTo(exOutput);
    }

     @Test
    void testDeleteWorks() throws Exception {

       
        Mockito.when(service.deleteData(Mockito.anyString())).thenReturn(true);
       
        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/supplier/delete/ab"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Deleted Successfully"))
        .andReturn().getResponse()
        .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        String expected = "Deleted Successfully";
       
        assertEquals(result, expected);
    }

    @Test
    void testDeleteNotWorks() throws Exception {
            
        Mockito.when(service.deleteData(Mockito.anyString())).thenReturn(false);
        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/supplier/delete/ab"))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andReturn().getResponse()
        .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        String expected =  "Id not found";
        
        assertEquals(result,expected);
        }
}