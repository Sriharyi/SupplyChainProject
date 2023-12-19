package com.example.supplychain.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import com.example.supplychain.model.Facility;
import com.example.supplychain.model.FacilityAddress;
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
    void testThatCanFacilitycanbeGetAllElement() throws Exception {

        Mockito.when(service.getAllFacility()).thenReturn(new ArrayList<Facility>(Arrays.asList(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"aa"))));

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/facility/select/all")).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        ArrayList op = new ObjectMapper().readValue(result, ArrayList.class);
        // .andReturn().getResponse().getContent;
        assertFalse(op.equals(null));

    }

    @Test
    void testThatCanFacilitycanbeGetAllElementThrowsException() throws Exception {

        Mockito.when(service.getAllFacility()).thenThrow(RuntimeException.class);

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/facility/select/all"))
        .andExpect(status().isBadRequest()).
        andExpect(MockMvcResultMatchers.content().
        json(new ObjectMapper().writeValueAsString(new ArrayList<Facility>(Arrays.asList(new Facility()))))).andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        ArrayList op = new ObjectMapper().readValue(result, ArrayList.class);
        // .andReturn().getResponse().getContent;
        assertFalse(op.equals(null));
    }
    
    @Test
    void testThatCanFacilitycanbeGetUsingId() throws Exception {

        Mockito.when(service.getById(Mockito.anyString())).thenReturn(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"aa"));

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
    void testThatCanFacilitycanNotbeGetUsingWrongId() throws Exception {

        Mockito.when(service.getById(Mockito.anyString())).thenReturn(null);

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/facility/select/cd"))
        .andExpect(status().isNotFound()).
        andExpect(MockMvcResultMatchers.content().
        json(new ObjectMapper().writeValueAsString(new Facility())))
        .andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        Facility op = new ObjectMapper().readValue(result, Facility.class);
        // .andReturn().getResponse().getContent;
         Assertions.assertThat(op).isEqualTo(new Facility());

    }

    @Test
    void testThatCanFacilitycanNotbeGetUsingThrowsException() throws Exception {

        Mockito.when(service.getById(Mockito.anyString())).thenThrow(RuntimeException.class);

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/facility/select/cd"))
        .andExpect(status().isBadRequest()).
        andExpect(MockMvcResultMatchers.content().
        json(new ObjectMapper().writeValueAsString(new Facility())))
        .andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        Facility op = new ObjectMapper().readValue(result, Facility.class);
        // .andReturn().getResponse().getContent;
         Assertions.assertThat(op).isEqualTo(new Facility());

    }

    @Test
    void testDeleteWorks() throws Exception {
        Mockito.when(service.getById(Mockito.anyString())).thenReturn(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"aa"));

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
    void testDeleteWorksWithWrongId() throws Exception {
        // Mockito.when(service.getById(Mockito.anyString())).thenReturn(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build()));
        Mockito.when(service.deleteData("ab")).thenReturn(false);
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
    void testDeleteNotWorksThrowsException() throws Exception {
        Mockito.when(service.getById(Mockito.anyString())).thenReturn(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"aa"));
        Mockito.when(service.deleteData("ab")).thenThrow(RuntimeException.class);
        // (new Facility("ab","Sai",new Address("aa","aa","aa","aa","aa"), new Supplier("aa","ss",new Address("aa","aa","aa","aa","aa"),"aa","aa","aa","aa","aa","aa"),new ArrayList<String>(Arrays.asList("aa"))));
        String result = mockMvc.
        perform(MockMvcRequestBuilders.
        delete("/facility/delete/ab")).
        andExpect(status().isBadRequest()).
        andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

        Boolean op = new ObjectMapper().readValue(result, Boolean.class);
        // .andReturn().getResponse().getContent;
        assertFalse(op.equals(null));

    }

    @Test
    public void testThatSupplierCanBeSavedWorks() throws Exception{
        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"aa");
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
    public void testThatSupplierCanBeSavedNotWorksThrowsException() throws Exception{
        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"aa");
        Mockito.when(service.saveData(Mockito.any(Facility.class))).thenThrow(RuntimeException.class);
        String result = mockMvc.perform(MockMvcRequestBuilders
                                        .post("/facility/save").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(facility)))
                                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                        .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(new Facility())))
                                        .andReturn().getResponse()
                                        .getContentAsString();
                                        System.out.println("_____");
                                        System.out.println(result);
                                        System.out.println("_____");
        Facility actualOutput = new ObjectMapper().readValue(result, Facility.class);  
        Assertions.assertThat(actualOutput).isNotEqualTo(facility);
    }

    @Test
    public void testThatFacilityCanbeUpdatedSuccessfully() throws JsonProcessingException, UnsupportedEncodingException, Exception
    {
        Facility expectedOutput = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"aa");
        
        Mockito.when(service.updateData(Mockito.any(Facility.class))).thenReturn(expectedOutput);

        String result = mockMvc.perform(MockMvcRequestBuilders
                                        .put("/facility/update").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(expectedOutput)))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(expectedOutput)))
                                        .andReturn().getResponse().getContentAsString();
        String exOutput = new ObjectMapper().writeValueAsString(expectedOutput);
        Assertions.assertThat(result).isEqualTo(exOutput);
    }

    @Test
    public void testThatFacilityCanNotbeUpdatedThrowsException() throws JsonProcessingException, UnsupportedEncodingException, Exception
    {
        // Facility expectedOutput = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        
        Mockito.when(service.updateData(Mockito.any(Facility.class))).thenThrow(RuntimeException.class);

        String result = mockMvc.perform(MockMvcRequestBuilders
                                        .put("/facility/update").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(new Facility())))
                                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(new Facility())))
                                        .andReturn().getResponse().getContentAsString();
        
        String exOutput = new ObjectMapper().writeValueAsString(new Facility());
        Assertions.assertThat(result).isEqualTo(exOutput);
    }

    @Test
    void testThatCanImagecanbeGetUsingId() throws Exception {

        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"D:/SupplyChainProject/src/main/resources/images/facilitiesImage/WhatsApp Image 2023-12-13 at 6.51.39 PM.jpeg");
         Mockito.when(service.getById(Mockito.anyString())).thenReturn(facility);
        Mockito.when(service.downloadImage(facility)).thenReturn(new byte[]{});

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/facility/downloadImage/cd")).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");


    }

    @Test
    void testThatCanImagecanbeGetUsingIdThrowsException() throws Exception {

        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"D:/SupplyChainProject/src/main/resources/images/facilitiesImage/WhatsApp Image 2023-12-13 at 6.51.39 PM.jpeg");
         Mockito.when(service.getById(Mockito.anyString())).thenReturn(facility);
        Mockito.when(service.downloadImage(facility)).thenThrow(RuntimeException.class);

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/facility/downloadImage/cd")).andExpect(status().isBadRequest()).andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

    }

    @Test
    void testThatCanImagecanNotbeGetUsingId() throws Exception {

        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"D:/SupplyChainProject/src/main/resources/images/facilitiesImage/WhatsApp Image 2023-12-13 at 6.51.39 PM.jpeg");
         Mockito.when(service.getById(Mockito.anyString())).thenReturn(new Facility());

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/facility/downloadImage/cd")).andExpect(status().isNotFound()).andReturn().getResponse()
                .getContentAsString();
        System.out.println("___________");
        System.out.println(result);
        System.out.println("___________");

    }

    @Test
    public void testThatImageUploadsWork() throws JsonProcessingException, UnsupportedEncodingException, Exception
    {
        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"D:/SupplyChainProject/src/main/resources/images/facilitiesImage/WhatsApp Image 2023-12-13 at 6.51.39 PM.jpeg");
        String name = "image";
        String originalFileName = "img.jpg";
        String contentType = "image/jpg";
        byte[] content = null;
        MultipartFile file = new MockMultipartFile(name,originalFileName, contentType, content);
        Mockito.when(service.getById(Mockito.anyString())).thenReturn(facility);
        Mockito.when(service.uploadImageToDB(Mockito.any(),Mockito.any(MultipartFile.class))).thenReturn(true);
        String result = mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.PUT,"/facility/updateImage/cd")
                                        .file(new MockMultipartFile("image",originalFileName,contentType,content)))
                                        .andExpect(MockMvcResultMatchers.status().isOk())
                                        .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testThatImageUploadsNotWork() throws JsonProcessingException, UnsupportedEncodingException, Exception
    {
        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"D:/SupplyChainProject/src/main/resources/images/facilitiesImage/WhatsApp Image 2023-12-13 at 6.51.39 PM.jpeg");
        String name = "image";
        String originalFileName = "img.jpg";
        String contentType = "image/jpg";
        byte[] content = null;
        MultipartFile file = new MockMultipartFile(name,originalFileName, contentType, content);
        Mockito.when(service.getById(Mockito.anyString())).thenReturn(facility);
        Mockito.when(service.uploadImageToDB(Mockito.any(),Mockito.any(MultipartFile.class))).thenReturn(false);
        String result = mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.PUT,"/facility/updateImage/cd")
                                        .file(new MockMultipartFile("image",originalFileName,contentType,content)))
                                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                                        .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testThatImageUploadsThrowsException() throws JsonProcessingException, UnsupportedEncodingException, Exception
    {
        Facility facility = new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build(),"D:/SupplyChainProject/src/main/resources/images/facilitiesImage/WhatsApp Image 2023-12-13 at 6.51.39 PM.jpeg");
        String name = "image";
        String originalFileName = "img.jpg";
        String contentType = "image/jpg";
        byte[] content = null;
        MultipartFile file = new MockMultipartFile(name,originalFileName, contentType, content);
        Mockito.when(service.getById(Mockito.anyString())).thenReturn(facility);
        Mockito.when(service.uploadImageToDB(Mockito.any(),Mockito.any(MultipartFile.class))).thenThrow(RuntimeException.class);
        String result = mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.PUT,"/facility/updateImage/cd")
                                        .file(new MockMultipartFile("image",originalFileName,contentType,content)))
                                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                        .andReturn().getResponse().getContentAsString();
    }
    
}
