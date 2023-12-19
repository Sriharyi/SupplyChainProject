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
                List<Brand> brands = new ArrayList<Brand>(Arrays.asList());
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

        @Test
        void testSelectAllIfNoDataAvailable() throws Exception {
                List<Brand> brands = new ArrayList<Brand>();
                Mockito.when(brandService.getAllBrand()).thenReturn(null);
                String result = mockMvc.perform(MockMvcRequestBuilders.get("/brand/select/all"))
                                .andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
                System.out.println("testSelectAllIfNoDataAvailable");
                System.out.println(result);
                System.out.println("___________");
                List<Brand> output = Arrays.asList(new ObjectMapper().readValue(result, Brand[].class));
                assertEquals(brands, output);
        }

        @Test
        void testSelectAllIfThrowsException() throws Exception {
                List<Brand> brands = new ArrayList<Brand>();
                Mockito.when(brandService.getAllBrand()).thenThrow(RuntimeException.class);
                String result = mockMvc.perform(MockMvcRequestBuilders.get("/brand/select/all"))
                                .andExpect(status().isInternalServerError()).andReturn().getResponse()
                                .getContentAsString();
                System.out.println("testSelectAllIfThrowsException");
                System.out.println(result);
                System.out.println("___________");
                List<Brand> output = Arrays.asList(new ObjectMapper().readValue(result,
                                Brand[].class));
                assertEquals(brands, output);
        }

        @Test
        void testSelectByIdIfDataAvailable() throws Exception {
                Brand brand = new Brand("abc", "bcd", "cde", "efg", "skfj");
                Mockito.when(brandService.getById(Mockito.anyString()))
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
                Mockito.when(brandService.getById(Mockito.anyString()))
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
        void testSelectByIdIfThrowsException() throws Exception {
                Brand brand = new Brand();
                Mockito.when(brandService.getById("abc"))
                                .thenThrow(RuntimeException.class);
                String result = mockMvc.perform(MockMvcRequestBuilders.get("/brand/select/abc"))
                                .andExpect(status().isInternalServerError())
                                .andReturn().getResponse().getContentAsString();
                System.out.println("testSelectByIdIfDataNotAvailable");
                System.out.println(result);
                System.out.println("___________");
                Brand output = new ObjectMapper().readValue(result, Brand.class);
                assertEquals(brand, output);
        }

        @Test
        void testUpdateBrandIfDataAvailable() throws Exception {
                Brand brand = new Brand("abc", "bcd", "cde", "efg", "skfj");
                Mockito.when(brandService.getById("abc"))
                                .thenReturn(brand);
                Mockito.when(brandService.updateBrand(brand)).thenReturn(brand);

                String result = mockMvc.perform(MockMvcRequestBuilders
                                .put("/brand/update").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(brand)))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn().getResponse()
                                .getContentAsString();

                System.out.println("testUpdateBrandIfDataAvailable");
                System.out.println(result);
                System.out.println("___________");
                Brand output = new ObjectMapper().readValue(result, Brand.class);
                assertEquals(brand, output);
        }

        @Test
        void testUpdateBrandIfDataNotAvailable() throws Exception {
                Brand brand = new Brand("abc", "bcd", "cde", "efg", "skfj");
                Brand expectedOutput = new Brand();
                Mockito.when(brandService.getById("abc"))
                                .thenReturn(null);

                String result = mockMvc.perform(MockMvcRequestBuilders
                                .put("/brand/update").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(brand)))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andReturn().getResponse()
                                .getContentAsString();

                System.out.println("testUpdateBrandIfDataNotAvailable");
                System.out.println(result);
                System.out.println("___________");
                Brand output = new ObjectMapper().readValue(result, Brand.class);
                assertEquals(expectedOutput, output);
        }

        @Test
        void testUpdateBrandIfThrowsException() throws Exception {
                Brand brand = new Brand("abc", "bcd", "cde", "efg", "skfj");
                Brand expectedOutput = new Brand();
                Mockito.when(brandService.getById("abc"))
                                .thenThrow(RuntimeException.class);

                String result = mockMvc.perform(MockMvcRequestBuilders
                                .put("/brand/update").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(brand)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andReturn().getResponse()
                                .getContentAsString();

                System.out.println("testUpdateBrandIfThrowsException");
                System.out.println(result);
                System.out.println("___________");
                Brand output = new ObjectMapper().readValue(result, Brand.class);
                assertEquals(expectedOutput, output);
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
        public void testInsertBrandIfDataAlreadyAvailable() throws Exception {

                Brand brand = new Brand();

                Mockito.when(brandService.saveData(Mockito.any(Brand.class))).thenReturn(null);

                String result = mockMvc.perform(MockMvcRequestBuilders
                                .post("/brand/save").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(brand)))
                                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                                .andReturn().getResponse()
                                .getContentAsString();
                System.out.println("___________");
                System.out.println(result);
                System.out.println("___________");

                Brand actualOutput = new ObjectMapper().readValue(result, Brand.class);

                assertEquals(brand, actualOutput);
        }

        @Test
        public void testInsertBrandIfThrowsException() throws Exception {

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

        @Test
        void testDeleteIfDataAvailable() throws Exception {
                Brand brand = new Brand("asd", "adf", "add", "sfd", "asd");
                Mockito.when(brandService.getById(Mockito.anyString())).thenReturn(brand);
                Mockito.when(brandService.deleteData(Mockito.anyString())).thenReturn("Deleted Successfully");

                String result = mockMvc.perform(MockMvcRequestBuilders.delete("/brand/delete/id"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn().getResponse()
                                .getContentAsString();
                System.out.println("testDeleteIfDataNotAvailable");
                System.out.println(result);
                System.out.println("___________");

                String expected = "Deleted Successfully";
                assertEquals(result, expected);
        }

        @Test
        void testDeleteIfDataNotAvailable() throws Exception {

                Mockito.when(brandService.deleteData(Mockito.anyString())).thenReturn("Id not found");
                // Mockito.when(brandService.getById(Mockito.anyString())).thenReturn(null);
                String result = mockMvc.perform(MockMvcRequestBuilders.delete("/brand/delete/id"))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andReturn().getResponse()
                                .getContentAsString();
                System.out.println("testDeleteIfDataNotAvailable");
                System.out.println(result);
                System.out.println("___________");

                String expected = "Id not found";
                assertEquals(result, expected);
        }

        @Test
        void testDeleteIfThrowsException() throws Exception {

                Mockito.when(brandService.getById(Mockito.anyString())).thenThrow(RuntimeException.class);
                Mockito.when(brandService.deleteData(Mockito.anyString())).thenThrow(RuntimeException.class);
                String result = mockMvc.perform(MockMvcRequestBuilders.delete("/brand/delete/id"))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andReturn().getResponse()
                                .getContentAsString();
                System.out.println("testDeleteIfDataNotAvailable");
                System.out.println(result);
                System.out.println("___________");

                String expected = "Internal error";
                assertEquals(result, expected);
        }
}