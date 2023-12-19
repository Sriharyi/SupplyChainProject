package com.example.supplychain.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.supplychain.model.Brand;
import com.example.supplychain.service.BrandServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = BrandController.class)
public class BrandControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
        private BrandServiceInterface brandService;

        @Test
        void testSelectAllIfDataAvailable() throws Exception {
                List<Brand> brands = new ArrayList<Brand>(Arrays.asList(new Brand("abc", "bcd", "cde", "asd", "efg")));
                Mockito.when(brandService.getAllBrand())
                                .thenReturn(brands);
                String result = mockMvc.perform(MockMvcRequestBuilders.get("/brand/select/all"))
                                .andExpect(status().isOk())
                                .andReturn().getResponse().getContentAsString();
                System.out.println("testSelectAllIfDataAvailable");
                System.out.println(result);
                System.out.println("___________");
                List<Brand> output = Arrays.asList(new ObjectMapper().readValue(result, Brand[].class));
                assertEquals(brands, output);
                ;
        }

        // @Test
        // void testSelectAllIfNoDataAvailable() throws Exception {
        //         List<Brand> brands = new ArrayList<Brand>(Arrays.asList(new Brand()));
        //         Mockito.when(brandService.getAllBrand()).thenReturn(null);
        //         String result = mockMvc.perform(MockMvcRequestBuilders.get("/brand/select/all"))
        //                         .andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
        //         System.out.println("testSelectAllIfNoDataAvailable");
        //         System.out.println(result);
        //         System.out.println("___________");
        //         List<Brand> output = Arrays.asList(new ObjectMapper().readValue(result, Brand[].class));
        //         assertEquals(brands, output);
        // }

        @Test
        void testSelectByIdIfDataAvailable() throws Exception {
                Brand brand = new Brand("abc", "bcd", "cde", "efg", "skfj");
                Mockito.when(brandService.getById("abc"))
                                .thenReturn(brand);
                String result = mockMvc.perform(MockMvcRequestBuilders.get("/brand/select/abc"))
                                .andExpect(status().isOk())
                                .andReturn().getResponse().getContentAsString();
                System.out.println("testSelectByIdIfDataAvailable");
                System.out.println(result);
                System.out.println("___________");
                Brand output = new ObjectMapper().readValue(result, Brand.class);
                assertEquals(brand, output);
        }

        @Test
        void testSelectByIdIfDataNotAvailable() throws Exception {
                Brand brand = new Brand();
                Mockito.when(brandService.getById("abc"))
                                .thenReturn(null);
                String result = mockMvc.perform(MockMvcRequestBuilders.get("/brand/select/abc"))
                                .andExpect(status().isNotFound())
                                .andReturn().getResponse().getContentAsString();
                System.out.println("testSelectByIdIfDataNotAvailable");
                System.out.println(result);
                System.out.println("___________");
                Brand output = new ObjectMapper().readValue(result, Brand.class);
                assertEquals(brand, output);
        }

        @Test
        void testDeleteIfDataNotAvailable() throws Exception {

                Mockito.when(brandService.deleteData(Mockito.anyString())).thenReturn(true);
                String result = mockMvc.perform(MockMvcRequestBuilders.delete("/brand/delete/id"))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andExpect(MockMvcResultMatchers.content().string("Id not found"))
                                .andReturn().getResponse()
                                .getContentAsString();
                System.out.println("testDeleteIfDataNotAvailable");
                System.out.println(result);
                System.out.println("___________");

                String expected = "Id not found";
                assertEquals(result, expected);
        }

        @Test
        public void testInsertBrand() throws Exception {

                Brand brand = new Brand("adc", "cde", "sd", "sdsf", "fan");

                Mockito.when(brandService.saveData(Mockito.any(Brand.class))).thenReturn(brand);

                String result = mockMvc.perform(MockMvcRequestBuilders
                                .post("/brand/save").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(brand)))
                                .andExpect(MockMvcResultMatchers.status().isCreated())
                                .andExpect(MockMvcResultMatchers.content()
                                                .json(new ObjectMapper().writeValueAsString(brand)))
                                .andReturn().getResponse()
                                .getContentAsString();
                System.out.println("___________");
                System.out.println(result);
                System.out.println("___________");

                Brand actualOutput = new ObjectMapper().readValue(result, Brand.class);

                assertEquals(brand, actualOutput);
        }

        @Test
        public void testInsertBrandIfDataNotInserted() throws Exception {

                Brand brand = new Brand("adc", "cde", "sd", "sdsf", "asd");
                Mockito.when(brandService.saveData(Mockito.any(Brand.class))).thenThrow(RuntimeException.class);

                String result = mockMvc.perform(MockMvcRequestBuilders
                                .post("/brand/save"))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andReturn().getResponse()
                                .getContentAsString();
                System.out.println("testInsertBrandIfDataNotInserted");
                System.out.println(result);
                System.out.println("___________");

                assertNotEquals(brand, result);
        }
}