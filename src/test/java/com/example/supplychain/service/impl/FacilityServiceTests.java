package com.example.supplychain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.example.supplychain.model.Facility;
import com.example.supplychain.model.FacilityAddress;
import com.example.supplychain.model.Supplier;
import com.example.supplychain.repository.FacilityRepository;
import com.example.supplychain.service.FacilityServiceInterface;
import com.example.supplychain.service.impl.FacilityService;


@ExtendWith(MockitoExtension.class)
public class FacilityServiceTests {
    @Mock
    private FacilityRepository repo;
    
    @InjectMocks
    private FacilityService service;

    @Test
    public void testThatFacilityCanbeCreated() throws Exception{
        Facility facility=new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        Mockito.when(repo.save(facility)).thenReturn(facility);
        Facility result = service.saveData(facility);
        assertEquals(facility, result);
    }

    @Test
    public void testThatFacilityCanNotbeCreated() throws Exception{
        Facility facility=new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        Mockito.when(repo.save(facility)).thenThrow(RuntimeException.class);
        Facility result = service.saveData(facility);
        assertNotEquals(facility, result);
    }

    @Test
    public void testThatFacilitygetAllFacilityWorks() throws Exception{
        List<Facility> facility=new ArrayList<Facility>();
        facility.add(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build()));
        Mockito.when(repo.findAll()).thenReturn(facility);
        List<Facility> result = service.getAllFacility();
        assertEquals(facility, result);
    }

    @Test
    public void testThatFacilitygetAllFacilityNotWorks() throws Exception{
        List<Facility> facility=new ArrayList<Facility>();
        facility.add(new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build()));
        Mockito.when(repo.findAll()).thenThrow(RuntimeException.class);
        List<Facility> result = service.getAllFacility();
        assertNotEquals(facility, result);
    }

    @Test
    public void testThatFacilitygetByIdWorks() throws Exception{
        Facility facility=new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        Mockito.when(repo.findById("cd")).thenReturn(Optional.of(facility));
        Facility result = service.getById("cd");
        assertEquals(facility, result);
    }

    @Test
    public void testThatFacilitygetByIdNotWorks() throws Exception{
         Facility facility=new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        Mockito.when(repo.findById("cd")).thenThrow(RuntimeException.class);
        Facility result = service.getById("cd");
        assertNotEquals(facility, result);
    }

    @Test
    public void testThatFacilityupdateDataWorks() throws Exception{
        Facility facility=new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        Mockito.when(repo.save(facility)).thenReturn(facility);
        Facility result = service.updateData(facility);
        assertEquals(facility, result);
    }

    @Test
    public void testThatFacilityupdateDataNotWorks() throws Exception{
        Facility facility=new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        Mockito.when(repo.save(facility)).thenThrow(RuntimeException.class);
        Facility result = service.updateData(facility);
        assertNotEquals(facility, result);
    }

    @Test
    public void testThatFacilitydeleteDataWorks() throws Exception{
        Facility facility=new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        Mockito.when(repo.deleteBy_id("cd")).thenReturn(true);
        Boolean result = service.deleteData("cd");
        assertEquals(true, result);
    }

    @Test
    public void testThatFacilitydeleteDataNotWorks() throws Exception{
        Facility facility=new Facility("cd","Sai",new FacilityAddress("aa","aa","aa","aa","aa"),Supplier.builder()._id("2354542345").build());
        Mockito.when(repo.deleteBy_id("cd")).thenThrow(RuntimeException.class);
        Boolean result = service.deleteData("cd");
        assertEquals(false, result);
    }

}
